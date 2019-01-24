package com.puzzlebench.clean_marvel_kotlin.domain.usecase

import com.puzzlebench.clean_marvel_kotlin.data.service.CharacterServicesImpl
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character import io.reactivex.Observable

open class GetCharacterDetailsServiceUseCase(private val characterServiceImp: CharacterServicesImpl) {
    open operator fun invoke(characterId: Int): Observable<List<Character>>
            = characterServiceImp.getCharactersDetails(characterId)
}