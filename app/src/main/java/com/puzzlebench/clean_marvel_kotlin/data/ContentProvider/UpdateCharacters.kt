package com.puzzlebench.clean_marvel_kotlin.data.ContentProvider

import com.puzzlebench.clean_marvel_kotlin.domain.model.Character

interface UpdateCharacters {
    fun updateCharacters(characters: List<Character>)
}