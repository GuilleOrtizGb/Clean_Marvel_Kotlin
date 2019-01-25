package com.puzzlebench.clean_marvel_kotlin.presentation.mvp

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.data.service.CharacterServicesImpl
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterDetailsServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.presentation.base.BaseRxDialogFragment

class CharacterDetailFragment: BaseRxDialogFragment(){

    var characterId: Int=0
    var getCharacterDetailsServiceUseCase=GetCharacterDetailsServiceUseCase(CharacterServicesImpl())
    val presenter= CharacterDetailPresenter(CharacterDetailView(this),
            getCharacterDetailsServiceUseCase,subscriptions)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_character_dialog_detail,container,false)
    }

    override fun onStart() {
        super.onStart()
        presenter.init()
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