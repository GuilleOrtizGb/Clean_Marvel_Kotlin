package com.puzzlebench.clean_marvel_kotlin.domain.usecase

import com.puzzlebench.clean_marvel_kotlin.data.service.CharacterServicesImpl
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character import io.reactivex.Observable
import io.reactivex.Single

open class GetCharacterDetailsServiceUseCase(private val characterServiceImp: CharacterServicesImpl) {
    open operator fun invoke(characterId: Int): Single<List<Character>>
            = characterServiceImp.getCharactersDetails(characterId)
}