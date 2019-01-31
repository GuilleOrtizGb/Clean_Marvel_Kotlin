package com.puzzlebench.clean_marvel_kotlin.data.mapper

import com.puzzlebench.clean_marvel_kotlin.domain.model.CharacterRealm
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.model.Thumbnail
import com.puzzlebench.clean_marvel_kotlin.domain.model.ThumbnailRealm

class CharacterMapperSave: BaseMapperRepository<Character,CharacterRealm>{
    override fun transformToResponse(type: CharacterRealm)= Character (
            type.id,
            type.name,
            type.description,
            trasnformToThumbnail(type.thumbnail)

    )

    private fun trasnformToThumbnail(thumbnail: ThumbnailRealm?)= Thumbnail (

    )

    override fun transform(type: Character)= CharacterRealm (

            type.id,
            type.name,
            type.description,
            trasnformToThumbnailRealm(type.thumbnail)
    )

    private fun trasnformToThumbnailRealm(thumbnail: Thumbnail)= ThumbnailRealm (
            thumbnail.path,
            thumbnail.extension
    )

    fun transformCharacterListToCharacterRealmList(listCharacters: List<Character>)
            = listCharacters.map {
                transform(it)
            }
}