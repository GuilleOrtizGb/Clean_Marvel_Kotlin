package com.puzzlebench.clean_marvel_kotlin.presentation.mvp

import com.puzzlebench.clean_marvel_kotlin.Utils.Constant
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.getImageByUrl
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.showToast
import kotlinx.android.synthetic.main.fragment_character_dialog_detail.*
import java.lang.ref.WeakReference

class CharacterDetailView(val fragment: CharacterDetailFragment){

    var characterId: Int = Constant.DEFAULT_INT_VALUE

    fun init() {

        if (fragment != null) {
            characterId = fragment.characterId
        }
    }

    fun showCharacterDetals(character: Character){
        fragment.textView_fragment_dialog_character_name.text=character.name
        fragment.textView_character_fragment_dialog_description.text=character.description
        var img= character.thumbnail.path+"."+character.thumbnail.extension
        fragment.imageView_fragment_dialog.getImageByUrl(img)

    }

    fun showToast(text: String) {
        fragment.activity.showToast(text)
    }

}