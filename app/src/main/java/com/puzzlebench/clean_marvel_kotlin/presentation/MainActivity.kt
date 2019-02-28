package com.puzzlebench.clean_marvel_kotlin.presentation

import android.os.Bundle
import android.view.View
import com.puzzlebench.clean_marvel_kotlin.MainApplication
import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.Utils.DebouncedOnClickListener
import com.puzzlebench.clean_marvel_kotlin.data.service.CharacterServicesImpl
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharactersSaveUseCase
import com.puzzlebench.clean_marvel_kotlin.presentation.base.BaseRxActivity
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.CharacterPresenter
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.CharecterView
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

open class MainActivity : BaseRxActivity() {
    @Inject
    lateinit var getCharactersSaveUseCase: GetCharactersSaveUseCase
    @Inject
    lateinit var getCharacterServiceUseCase:GetCharacterServiceUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MainApplication.appComponent.inject(this)
        //DaggerAppComponent.create().inject(this)

        val presenter = CharacterPresenter(CharecterView(this), getCharacterServiceUseCase,
                getCharactersSaveUseCase, subscriptions)

        presenter.init()

        this.floatingActionButton.setOnClickListener(DebouncedOnClickListener(View.OnClickListener {
            presenter.fabListener()
        }))
    }
}