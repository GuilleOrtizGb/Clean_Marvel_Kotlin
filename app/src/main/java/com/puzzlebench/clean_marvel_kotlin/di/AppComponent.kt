package com.puzzlebench.clean_marvel_kotlin.di

import com.puzzlebench.clean_marvel_kotlin.presentation.MainActivity
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.CharacterDetailFragment
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent{
    fun inject(app: MainActivity)
    fun inject(detailFragment: CharacterDetailFragment)
}