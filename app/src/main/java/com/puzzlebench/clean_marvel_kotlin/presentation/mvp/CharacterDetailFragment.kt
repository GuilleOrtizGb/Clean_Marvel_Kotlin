package com.puzzlebench.clean_marvel_kotlin.presentation.mvp

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.presentation.base.BaseRxDialogFragment

class CharacterDetailFragment: BaseRxDialogFragment(){


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_character_dialog_detail,container,false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }

    companion object {
        fun newInstance(characterId: Int)=
                CharacterDetailFragment().apply {
                    arguments=Bundle().apply {
                        putInt("CHARACTER_ID",characterId)
                    }
                }
    }
}