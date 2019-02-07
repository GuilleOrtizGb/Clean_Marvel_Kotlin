package com.puzzlebench.clean_marvel_kotlin.data.database

import android.util.Log
import com.puzzlebench.clean_marvel_kotlin.data.ContentProvider.CharactersContract
import com.puzzlebench.clean_marvel_kotlin.data.mapper.CharacterMapperSave
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.model.CharacterRealm
import io.reactivex.Completable
import io.realm.Realm
import io.realm.RealmResults

open class ChatacterDataRepoImplementation(val mapper: CharacterMapperSave=CharacterMapperSave()):CharacterDataRepo{

    override fun saveCharacters(characterList: List<Character>)= Completable.fromCallable {


        Realm.getDefaultInstance().use {
            realm->
                realm.executeTransaction{
                    var realmList: List<CharacterRealm> = mapper.transformToRealmList(characterList)

                    // Test
                    realmList[0].name = "Data from Realm"

                    realm.insertOrUpdate(realmList)
                }
            }

        logAllCharacters()
    }

    private fun logAllCharacters() {

        val allSavedCharacterRealm = queryAllCharacters()

        allSavedCharacterRealm.forEach { character ->
            Log.v("Characters","character id ${character.id} name ${character.name} size ${allSavedCharacterRealm.size}")
        }
    }

    fun deleteQueryCharacters(queryCharacterRealm: RealmResults<CharacterRealm>) {

        Realm.getDefaultInstance().use {
            realm->
                realm.executeTransaction{

                    queryCharacterRealm.forEach { character ->
                        Log.v("Deleted CHaracters","DELETED character id ${character.id} name ${character.name} size ${queryCharacterRealm.size}")
                        character.deleteFromRealm()
                    }
                }
        }
    }

     fun queryAllCharacters(): List<Character>  {

         val realm: Realm = Realm.getDefaultInstance()

         val allSavedCharacterRealm = realm.where(CharacterRealm::class.java)
                .findAll()
        return mapper.transformRealm(allSavedCharacterRealm)
    }

    fun queryCharacterById(id: Int?): List<Character>  {

        val realm: Realm = Realm.getDefaultInstance()

        val allSavedCharacterRealm = realm.where(CharacterRealm::class.java)
                .equalTo(CharactersContract.COLUMN_ID, id)
                .findAll()

        return mapper.transformRealm(allSavedCharacterRealm)
    }
}