package com.puzzlebench.clean_marvel_kotlin.presentation.mvp

import com.puzzlebench.clean_marvel_kotlin.domain.model.CharacterRealm
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.presentation.base.Presenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmQuery
import io.realm.RealmResults
import java.util.*

class CharacterPresenter(view: CharecterView,
                         private val getChatacterServiceUseCase: GetCharacterServiceUseCase,
                         val subscriptions: CompositeDisposable) : Presenter<CharecterView>(view) {

    fun init() {
        view.init()
        requestGetCharacters()
        view.getFloatinButton().setOnClickListener{

            view.showToast("Refreshing")

            val realm: Realm = Realm.getDefaultInstance()

            realm.beginTransaction()

            val testCharacter2 = realm.createObject(CharacterRealm::class.java)
            testCharacter2.name="Superman"
            testCharacter2.description="Very super"

            val testCharacter3 = realm.createObject(CharacterRealm::class.java)
            testCharacter3.name="SpiderMan"
            testCharacter3.description="Spider"

            realm.commitTransaction()

            val allSavedCharacterRealm = realm.where(CharacterRealm::class.java)
                   .findAll()
            val savedCharacterRealmQuery = realm.where(CharacterRealm::class.java)
                    .equalTo("name","SpiderMAn").findAll()


            savedCharacterRealmQuery.forEach { character ->
                realm.beginTransaction()
                character.deleteFromRealm()
                realm.commitTransaction()
            }

            allSavedCharacterRealm.forEach { character ->
                view.showToast(character.name.toString())
                println("character "+character.name)
            }

        }
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
                    }
                    view.hideLoading()

                }, { e ->
                    view.hideLoading()
                    view.showToastNetworkError(e.message.toString())
                })
                subscriptions.add(subscription)
    }
}
