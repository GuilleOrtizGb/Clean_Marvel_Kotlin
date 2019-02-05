package com.puzzlebench.clean_marvel_kotlin.data.ContentProvider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import com.puzzlebench.clean_marvel_kotlin.data.database.ChatacterDataPersistenceImplementation
import com.puzzlebench.clean_marvel_kotlin.domain.model.CharacterRealm
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults



class CharactersContentProvider: ContentProvider(){

    val CODE_CHARACTER = 100
    val CODE_CHARACTER_WITH_ID = 101

    private val sUriMatcher: UriMatcher = buildUriMatcher()


    private fun buildUriMatcher(): UriMatcher {
        val matcher: UriMatcher =  UriMatcher(UriMatcher.NO_MATCH)
        val authority: String = CharactersContract.CONTENT_AUTHORITY

        // URI is content://com.puzzlebench.clean_marvel_kotlin/characters/*/
        matcher.addURI(authority,CharactersContract.TABLE_NAME,CODE_CHARACTER)

        // URI is content://com.puzzlebench.clean_marvel_kotlin/characters/123456/
        matcher.addURI(authority,CharactersContract.TABLE_NAME + "/#", CODE_CHARACTER_WITH_ID)

        return matcher

    }



    override fun onCreate(): Boolean {

        val config= RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()

        Realm.setDefaultConfiguration(config)

        return true
    }

    override fun insert(uri: Uri?, values: ContentValues?): Uri {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun query(uri: Uri?, projection: Array<out String>?,
                       selection: String?, selectionArgs: Array<out String>?,
                       sortOrder: String?): Cursor {

        var cursor: Cursor

        when(sUriMatcher.match(uri)){

            CODE_CHARACTER -> {
                var allCharacters: RealmResults<CharacterRealm> = ChatacterDataPersistenceImplementation()
                        .queryAllCharacters()

                cursor = createCursor(allCharacters)

            }

            CODE_CHARACTER_WITH_ID -> {
                var id: String? = uri?.lastPathSegment

                var characterById: RealmResults<CharacterRealm> = ChatacterDataPersistenceImplementation()
                        .queryCharacterById(id?.toInt())

                cursor = createCursor(characterById)

            }
            else -> {
                throw  UnsupportedOperationException(" uri Not found : " + uri);
            }
        }
        return cursor
    }

    private fun createCursor(allCharacters: RealmResults<CharacterRealm>): MatrixCursor {

        var columnNames: Array<String> = arrayOf("id", "name", "description", "thumbnail")

        var cursor = MatrixCursor(columnNames)

        allCharacters.forEach { character ->

            var cursorRow = arrayOf(
                    character.id,
                    character.name,
                    character.description,
                    character.thumbnail)

            cursor.addRow(cursorRow)

        }
        return cursor
    }

    override fun update(uri: Uri?, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(uri: Uri?, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getType(uri: Uri?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}