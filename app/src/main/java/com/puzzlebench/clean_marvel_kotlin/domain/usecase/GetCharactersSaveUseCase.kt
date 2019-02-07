package com.puzzlebench.clean_marvel_kotlin.domain.usecase

import com.puzzlebench.clean_marvel_kotlin.data.database.CharacterDataRepo
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import io.reactivex.Completable

open class GetCharactersSaveUseCase(private  val characterDataRepo: CharacterDataRepo){
    open operator fun invoke(characters: List<Character>): Completable
            = characterDataRepo.saveCharacters(characters)
}