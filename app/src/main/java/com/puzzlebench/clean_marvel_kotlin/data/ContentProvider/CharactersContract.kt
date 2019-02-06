package com.puzzlebench.clean_marvel_kotlin.data.ContentProvider

import android.net.Uri


 class CharactersContract{
    companion object {

        fun buildCharacterUriWithId(id: Int) = CONTENT_URI.buildUpon()
                .appendPath(id.toString())
                .build()

        val CONTENT_AUTHORITY = "com.puzzlebench.clean_marvel_kotlin"
        val BASE_CONTENT_URI= Uri.parse("content://$CONTENT_AUTHORITY")
        const val CHARACTERS_PATH = "characters"
        val CONTENT_URI= Uri.withAppendedPath(BASE_CONTENT_URI, CHARACTERS_PATH)

        const val TABLE_NAME: String = "characters"
        const val COLUMN_ID: String = "id"
        const val COLUMN_NAME: String = "name"
        const val COLUMN_DESCRIPTION: String = "description"
        const val COLUMN_THUMBNAIL: String = "thumbnail"
        const val COLUMN_THUMBNAIL_PATH: String = "path"
        const val COLUMN_THUMBNAIL_EXTENSION: String = "extension"

    }


}