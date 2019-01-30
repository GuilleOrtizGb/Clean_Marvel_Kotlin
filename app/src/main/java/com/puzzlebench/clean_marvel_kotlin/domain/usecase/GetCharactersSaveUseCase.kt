package com.puzzlebench.clean_marvel_kotlin.domain.usecase

import com.puzzlebench.clean_marvel_kotlin.data.database.CharacterDataPersistance
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import io.reactivex.Completable
import org.w3c.dom.CharacterData

open class GetCharactersSaveUseCase(private  val characterDataPersistance: CharacterDataPersistance){
    open operator fun invoke(characters: List<Character>): Completable
            = characterDataPersistance.saveCharacters(characters)

}