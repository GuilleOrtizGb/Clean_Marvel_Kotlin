package com.puzzlebench.clean_marvel_kotlin.mocks.factory

import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.model.Thumbnail


class CharactersFactory {

    companion object Factory {
        private const val ID = 1234
        private const val BASE_NAME = "Name"
        private const val BASE_DESCRIPTION = "Description"
        private const val BASE_PATH = "image"
        private const val BASE_EXTENSION = ".png"

        open fun getMockCharacter(): List<Character> {
            return (1..10).toList().map {
                Character(ID, BASE_NAME, "$BASE_DESCRIPTION${it}", Thumbnail(BASE_PATH, BASE_EXTENSION))
            }
        }
    }
}
