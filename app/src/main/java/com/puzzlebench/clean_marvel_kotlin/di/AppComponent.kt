package com.puzzlebench.clean_marvel_kotlin.di

import com.puzzlebench.clean_marvel_kotlin.presentation.MainActivity
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent{
    fun inject(app: MainActivity)//inject() in this class
}