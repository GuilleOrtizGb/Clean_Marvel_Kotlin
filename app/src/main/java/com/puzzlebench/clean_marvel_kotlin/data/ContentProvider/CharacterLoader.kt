package com.puzzlebench.clean_marvel_kotlin.data.ContentProvider

import android.app.LoaderManager
import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import com.puzzlebench.clean_marvel_kotlin.presentation.MainActivity
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.model.Thumbnail
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.showToast
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.CharacterPresenter
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.CharecterView

class  CharacterLoader(val context: MainActivity, val presenter: CharacterPresenter): LoaderManager.LoaderCallbacks<Cursor>{

    val showUpdatedCharacters: UpdateCharacters = presenter

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

    var characters: MutableList<Character> = mutableListOf()

     let{
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
    return  characters
}