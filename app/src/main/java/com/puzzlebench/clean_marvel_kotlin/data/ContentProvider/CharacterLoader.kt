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
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.CharacterDetailView
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.CharacterPresenter
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.CharecterView

class  CharacterLoader(val context: MainActivity, val view: CharecterView): LoaderManager.LoaderCallbacks<Cursor>{

    val showUpdatedCharacters: UpdateCharacters = view

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {

        val uri: String = CharactersContract.CONTENT_URI.toString()
        return CursorLoader(context, Uri.parse(uri),null,null,
                null,null)
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor) {
        var characters: List<Character> = data.toList(data)
        showUpdatedCharacters.updateCharacters(characters)
    }

    override fun onLoaderReset(loader: Loader<Cursor>?) {
    }
}

private fun  Cursor.toList(cursor: Cursor): MutableList<Character> {

    return generateSequence { if (cursor.moveToNext()) cursor else null }
                 .map { Character(
                         it.getInt(it.getColumnIndex(CharactersContract.COLUMN_ID)),
                         it.getString(it.getColumnIndex(CharactersContract.COLUMN_NAME)),
                         it.getString(it.getColumnIndex(CharactersContract.COLUMN_DESCRIPTION)),
                         Thumbnail(
                                 it.getString(it.getColumnIndex(CharactersContract.COLUMN_THUMBNAIL_PATH)),
                                 it.getString(it.getColumnIndex(CharactersContract.COLUMN_THUMBNAIL_EXTENSION))
                         )
                 ) }.toMutableList()
}