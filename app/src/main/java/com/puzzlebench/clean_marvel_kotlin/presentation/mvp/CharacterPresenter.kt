package com.puzzlebench.clean_marvel_kotlin.presentation.mvp

import com.puzzlebench.clean_marvel_kotlin.data.ContentProvider.CharacterLoader
import com.puzzlebench.clean_marvel_kotlin.data.ContentProvider.UpdateCharacters
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.model.CharacterRealm
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharactersSaveUseCase
import com.puzzlebench.clean_marvel_kotlin.presentation.base.Presenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm

class CharacterPresenter(view: CharecterView,
                         private val getChatacterServiceUseCase: GetCharacterServiceUseCase,
                         private val getChatacterSaveUseCase: GetCharactersSaveUseCase,
                         val subscriptions: CompositeDisposable) : Presenter<CharecterView>(view), UpdateCharacters {

    private val CHARACTER_LOADER_ID=101

    fun init() {
        view.init()
        view.activity.loaderManager.initLoader(CHARACTER_LOADER_ID,null, CharacterLoader(view.activity,this))
        requestGetCharacters()

        view.getFloatinButton().setOnClickListener{

            view.showLoading()
            requestGetCharacters()
        }
    }

    override fun updateCharacters(characters: List<Character>) {
        view.hideLoading()
        view.showCharacters(characters)
    }

    private fun requestGetCharacters() {
        val subscription =
                getChatacterServiceUseCase.invoke()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ characters ->
                    if (characters.isEmpty()) {
                        view.showToastNoItemToShow()
                    } else {
                        view.showCharacters(characters)

                        saveCharacters(characters)
                    }
                    view.hideLoading()

                }, { e ->
                    view.hideLoading()
                    view.showToastNetworkError(e.message.toString())
                })
                subscriptions.add(subscription)
    }

    private fun saveCharacters(characters: List<Character>) {

        val subscription =
                getChatacterSaveUseCase(characters)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    //view.showToast("Data saved")
                },{errorInSaving->
                    view.showToast(errorInSaving.message.toString())
                })

        subscriptions.add(subscription)
    }
}
