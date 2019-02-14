package com.puzzlebench.clean_marvel_kotlin.data.ContentProvider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import com.puzzlebench.clean_marvel_kotlin.data.database.ChatacterDataRepoImplementation
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import io.realm.Realm
import io.realm.RealmConfiguration

class CharactersContentProvider: ContentProvider(){

    val CODE_CHARACTER = 100
    val CODE_CHARACTER_WITH_ID = 101
    val PAD =  "/#"

    private val sUriMatcher: UriMatcher = buildUriMatcher()

    private fun buildUriMatcher(): UriMatcher {
        val matcher: UriMatcher =  UriMatcher(UriMatcher.NO_MATCH)
        val authority: String = CharactersContract.CONTENT_AUTHORITY

        // URI is content://com.puzzlebench.clean_marvel_kotlin/characters/*/
        matcher.addURI(authority,CharactersContract.TABLE_NAME,CODE_CHARACTER)

        // URI is content://com.puzzlebench.clean_marvel_kotlin/characters/123456/
        matcher.addURI(authority,CharactersContract.TABLE_NAME + PAD , CODE_CHARACTER_WITH_ID)

        return matcher
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun insert(uri: Uri?, values: ContentValues?): Uri {
        TODO("not implemented") // Do nothing
    }

    override fun query(uri: Uri?, projection: Array<out String>?,
                       selection: String?, selectionArgs: Array<out String>?,
                       sortOrder: String?): Cursor {

        var cursor: Cursor

        when(sUriMatcher.match(uri)){

            CODE_CHARACTER -> {
                var allCharacters: List<Character> = ChatacterDataRepoImplementation()
                        .queryAllCharacters().blockingGet()
                cursor = createCursor(allCharacters)
            }

            CODE_CHARACTER_WITH_ID -> {
                var id: String? = uri?.lastPathSegment

                var characterById: List<Character> = ChatacterDataRepoImplementation()
                        .queryCharacterById(id?.toInt())

                cursor = createCursor(characterById)
            }
            else -> {
                throw  UnsupportedOperationException(" uri Not found : " + uri);
            }
        }
        return cursor
    }

    private fun createCursor(allCharacters: List<Character>): MatrixCursor {

        var columnNames: Array<String> = arrayOf(CharactersContract.COLUMN_ID,
                CharactersContract.COLUMN_NAME,
                CharactersContract.COLUMN_DESCRIPTION,
                CharactersContract.COLUMN_THUMBNAIL_PATH,
                CharactersContract.COLUMN_THUMBNAIL_EXTENSION)

        var cursor = MatrixCursor(columnNames)

        allCharacters.forEach { character ->
            var cursorRow = arrayOf(
                    character.id,
                    character.name,
                    character.description,
                    character.thumbnail.path,
                    character.thumbnail.extension)
            cursor.addRow(cursorRow)
        }
        return cursor
    }

    override fun update(uri: Uri?, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("not implemented") // Do nothing
    }

    override fun delete(uri: Uri?, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("not implemented") // Do nothing
    }

    override fun getType(uri: Uri?): String {
        TODO("not implemented") // Do nothing
    }
}