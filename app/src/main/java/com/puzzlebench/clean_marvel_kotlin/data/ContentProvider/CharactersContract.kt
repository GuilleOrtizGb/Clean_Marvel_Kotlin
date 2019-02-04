package com.puzzlebench.clean_marvel_kotlin.data.ContentProvider

import android.net.Uri
import android.provider.BaseColumns

open class CharactersContract{
    companion object {
        val CONTENT_AUTHORITY = "com.puzzlebench.clean_marvel_kotlin"
        val BASE_CONTENT_URI= Uri.parse("content://$CONTENT_AUTHORITY")
        const val CHARACTERS_PATH = "characters"
        val CONTENT_URI= Uri.withAppendedPath(BASE_CONTENT_URI, CHARACTERS_PATH)

        const val TABLE_NAME: String = "characters"
        const val COLUMN_ID: String = "_id"
        const val COLUMN_DESCRIPTION: String = "description"
        const val COLUMN_THUMBNAIL: String = "thumbnail"

    }
}