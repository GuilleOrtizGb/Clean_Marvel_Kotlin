package com.puzzlebench.clean_marvel_kotlin.presentation.mvp

import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterDetailsServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.presentation.base.Presenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CharacterDetailPresenter(
        view: CharacterDetailView,
        private val getCharacterDetailsServiceUseCase: GetCharacterDetailsServiceUseCase,
        val subscrptions: CompositeDisposable) :Presenter<CharacterDetailView>(view){

    fun init(){
        view.init()
    }

    fun requestGetCharacterDetail(){
        val subscrption = getCharacterDetailsServiceUseCase(view.characterId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ character ->
                    if (character.isEmpty()) {
                        view.showToast("Character is empty")
                    } else {
                        view.showCharacterDetals(character [0])
                    }
                }, { e ->
                    view.showToast(e.message.toString())
                })
        subscrptions.add(subscrption)
    }
}