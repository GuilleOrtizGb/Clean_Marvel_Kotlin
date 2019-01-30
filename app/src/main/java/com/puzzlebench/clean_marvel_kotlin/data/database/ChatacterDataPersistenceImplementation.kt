package com.puzzlebench.clean_marvel_kotlin.data.database

import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.model.CharacterRealm
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmList



class ChatacterDataPersistenceImplementation():CharacterDataPersistance{

    override fun saveCharacters(characterList: List<Character>)= Completable.fromCallable {

        val realm: Realm = Realm.getDefaultInstance()

        realm.beginTransaction()
        var characterFromApi: Character=characterList.first()

        //TODO refactor extract transform functionality
        val testCharacter = realm.createObject(CharacterRealm::class.java)
        testCharacter.name = characterFromApi.name
        testCharacter.description = characterFromApi.description

        /*val allSavedCharacterRealm2 = realm.where(CharacterRealm::class.java)
                .findAll()
        allSavedCharacterRealm2.deleteAllFromRealm()*/

        realm.commitTransaction()

        val allSavedCharacterRealm = realm.where(CharacterRealm::class.java)
                .findAll()
        /*val savedCharacterRealmQuery = realm.where(CharacterRealm::class.java)
                .equalTo("name", "Black Panther").findAll()

        savedCharacterRealmQuery.forEach { character ->
            realm.beginTransaction()
            character.deleteFromRealm()
            realm.commitTransaction()
        }
        */

        allSavedCharacterRealm.forEach { character ->
            println("character " + character.name+" size "+allSavedCharacterRealm.size)
        }
    }
}