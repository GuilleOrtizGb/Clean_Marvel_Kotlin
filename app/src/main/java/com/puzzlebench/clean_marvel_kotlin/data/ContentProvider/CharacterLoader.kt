package com.puzzlebench.clean_marvel_kotlin.data.ContentProvider

import android.app.LoaderManager
import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import com.puzzlebench.clean_marvel_kotlin.presentation.MainActivity
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.model.Thumbnail

class  CharacterLoader(val context: MainActivity): LoaderManager.LoaderCallbacks<Cursor>{
    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {

        var uri: String = CharactersContract.CONTENT_URI.toString()
        var characterLoader= CursorLoader(context, Uri.parse(uri),null,null,
                null,null)

        return characterLoader
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor) {
        data.toList(data)

    }

    override fun onLoaderReset(loader: Loader<Cursor>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

private fun  Cursor?.toList(cursor: Cursor): MutableList<Character> {

    var characters: MutableList<Character> = mutableListOf()


     cursor?.let{
        it.moveToFirst()
        while (!it.isAfterLast()) {
            characters.add(
                    Character(
                            it.getInt(it.getColumnIndex(CharactersContract.COLUMN_ID)),
                            it.getString(it.getColumnIndex(CharactersContract.COLUMN_NAME)),
                            it.getString(it.getColumnIndex(CharactersContract.COLUMN_DESCRIPTION)),
                            Thumbnail(
                                    it.getString(it.getColumnIndex(CharactersContract.COLUMN_THUMBNAIL_PATH)),
                                    it.getString(it.getColumnIndex(CharactersContract.COLUMN_THUMBNAIL_EXTENSION))
                            )
                    )
            )
            it.moveToNext()
        }
    }

    /*
    * characters.add(
                        Character(
                                it.getInt(it.getColumnIndex(CharactersContract.COLUMN_ID)),
                                it.getString(it.getColumnIndex(CharactersContract.COLUMN_NAME)),
                                it.getString(it.getColumnIndex(CharactersContract.COLUMN_DESCRIPTION)),
                                Thumbnail(
                                        it.getString(it.getColumnIndex(CharactersContract.COLUMN_THUMBNAIL_PATH)),
                                        it.getString(it.getColumnIndex(CharactersContract.COLUMN_THUMBNAIL_EXTENSION))
                                )
                        )
                )*/


    return  characters

}


