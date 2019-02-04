package com.puzzlebench.clean_marvel_kotlin.data.database

import com.puzzlebench.clean_marvel_kotlin.data.mapper.CharacterMapperSave
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.model.CharacterRealm
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmResults


open class ChatacterDataPersistenceImplementation(val mapper: CharacterMapperSave=CharacterMapperSave()):CharacterDataPersistance{

    override fun saveCharacters(characterList: List<Character>)= Completable.fromCallable {

        val realm: Realm = Realm.getDefaultInstance()

        realm.executeTransaction{
            var realmList: List<CharacterRealm> = mapper.transformToRealmList(characterList)
            realm.insertOrUpdate(realmList)
        }

        logAllCharacters()
    }

    private fun logAllCharacters() {

        val allSavedCharacterRealm = queryAllCharacters()

        allSavedCharacterRealm.forEach { character ->
            println("character id ${character.id} name ${character.name} size ${allSavedCharacterRealm.size}")
        }
    }

    fun deleteQueryCharacters(realm: Realm, queryCharacterRealm: RealmResults<CharacterRealm>) {

         realm.executeTransaction{

             queryCharacterRealm.forEach { character ->
                 println("DELETED character id ${character.id} name ${character.name} size ${queryCharacterRealm.size}")
                 character.deleteFromRealm()
             }
         }
    }

     fun queryAllCharacters(): RealmResults<CharacterRealm>  {

         val realm: Realm = Realm.getDefaultInstance()

         val allSavedCharacterRealm = realm.where(CharacterRealm::class.java)
                .findAll()

        return allSavedCharacterRealm
    }
}