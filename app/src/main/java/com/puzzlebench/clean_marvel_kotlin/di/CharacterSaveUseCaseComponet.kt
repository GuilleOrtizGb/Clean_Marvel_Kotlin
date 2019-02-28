package com.puzzlebench.clean_marvel_kotlin.di

import com.puzzlebench.clean_marvel_kotlin.presentation.MainActivity
import dagger.Component

@Component(modules = [CharactersSaveUseCaseModule::class])
interface CharacterSaveUseCaseComponet{
    fun inject(app: MainActivity)//inject() in this class
}