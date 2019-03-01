package com.puzzlebench.clean_marvel_kotlin.presentation.mvp

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.puzzlebench.clean_marvel_kotlin.MainApplication
import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.Utils.Constant
import com.puzzlebench.clean_marvel_kotlin.data.service.CharacterServicesImpl
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterDetailsServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.presentation.base.BaseRxDialogFragment
import javax.inject.Inject

class CharacterDetailFragment: BaseRxDialogFragment(){
    @Inject
    lateinit var getCharacterDetailsServiceUseCase: GetCharacterDetailsServiceUseCase

    var characterId: Int = Constant.DEFAULT_INT_VALUE

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_character_dialog_detail, container,false)
    }

    override fun onStart() {
        super.onStart()

        MainApplication.appComponent.inject(this)

        val presenter= CharacterDetailPresenter(CharacterDetailView(this),
                getCharacterDetailsServiceUseCase, subscriptions)
        presenter.init()
        presenter.requestGetCharacterDetail()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        arguments?.let {
            characterId = it.getInt(Constant.CHARACTER_ID)
        }

        val fragmentDialog=super.onCreateDialog(savedInstanceState)
        fragmentDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return  fragmentDialog
    }

    companion object {
       open fun newInstance(characterId: Int)=
                CharacterDetailFragment().apply {
                    arguments=Bundle().apply {
                        putInt(Constant.CHARACTER_ID, characterId)
                    }
                }
    }
}