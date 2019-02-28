package com.puzzlebench.clean_marvel_kotlin.di

import com.puzzlebench.clean_marvel_kotlin.data.database.CharacterDataRepo
import com.puzzlebench.clean_marvel_kotlin.data.database.ChatacterDataRepoImplementation
import com.puzzlebench.clean_marvel_kotlin.data.service.CharacterServicesImpl
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharactersSaveUseCase
import dagger.Module
import dagger.Provides

@Module
class AppModule{
    @Provides
    fun providesCharactersSaveUseCase(): GetCharactersSaveUseCase {
        return GetCharactersSaveUseCase(ChatacterDataRepoImplementation())
    }
    @Provides
    fun providesCharacterServiceUseCase(): GetCharacterServiceUseCase{
        return GetCharacterServiceUseCase(CharacterServicesImpl())
    }
}