package com.puzzlebench.clean_marvel_kotlin.data.database

import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import io.reactivex.Completable

interface CharacterDataRepo{
    fun saveCharacters(characterList: List<Character>): Completable
}
