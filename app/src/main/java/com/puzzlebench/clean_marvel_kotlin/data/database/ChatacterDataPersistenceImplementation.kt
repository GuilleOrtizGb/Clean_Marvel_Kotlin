package com.puzzlebench.clean_marvel_kotlin.data.database

import com.puzzlebench.clean_marvel_kotlin.data.mapper.CharacterMapperSave
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.model.CharacterRealm
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmList



class ChatacterDataPersistenceImplementation(val mapper: CharacterMapperSave=CharacterMapperSave()):CharacterDataPersistance{

    override fun saveCharacters(characterList: List<Character>)= Completable.fromCallable {

        val realm: Realm = Realm.getDefaultInstance()

        realm.beginTransaction()

        val allSavedCharacterRealm = realm.where(CharacterRealm::class.java)
                .findAll()

        allSavedCharacterRealm.forEach { character ->
            println("DELETED character id ${character.id} name ${character.name} size ${allSavedCharacterRealm.size}")
            character.deleteFromRealm()
        }

        insertNewValues(realm, characterList)

        val allSavedCharacterRealm2 = realm.where(CharacterRealm::class.java)
                .findAll()

        allSavedCharacterRealm2.forEach { character ->
            println("character id ${character.id} name ${character.name} size ${allSavedCharacterRealm.size}\"")
        }
        realm.commitTransaction()
    }

    private fun insertNewValues(realm: Realm, characterList: List<Character>) {


        var realmList: List<CharacterRealm> = mapper.transformCharacterListToCharacterRealmList(characterList)
        realm.insert(realmList)


    }
}