package com.puzzlebench.clean_marvel_kotlin.presentation.mvp


import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.verify
import com.puzzlebench.clean_marvel_kotlin.data.database.ChatacterDataRepoImplementation
import com.puzzlebench.clean_marvel_kotlin.data.service.CharacterServicesImpl
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterDetailsServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharactersSaveUseCase
import com.puzzlebench.clean_marvel_kotlin.mocks.factory.CharactersFactory
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

import org.mockito.Mockito.*

class CharacterDetailPresenterTest{

    private var detailView: CharacterDetailView = mock(CharacterDetailView::class.java)
    private var characterServicesImpl: CharacterServicesImpl = mock(CharacterServicesImpl::class.java)


    private lateinit var characterDetailPresenter: CharacterDetailPresenter
    private lateinit var getCharacterDetailsServiceUseCase: GetCharacterDetailsServiceUseCase

    @Before
    fun setUp(){
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }

        getCharacterDetailsServiceUseCase = GetCharacterDetailsServiceUseCase(characterServicesImpl)
        val subscrptions = mock(CompositeDisposable::class.java)
        characterDetailPresenter = CharacterDetailPresenter(detailView, getCharacterDetailsServiceUseCase, subscrptions)
    }

    @Test
    fun responseWithError(){
        val errorMessage = "error"

        getCharacterDetailsServiceUseCase.stub {
            on { getCharacterDetailsServiceUseCase.invoke(1) } doReturn Single.error(Exception(errorMessage))
        }
        detailView.stub {
            on { detailView.characterId } doReturn 1
        }

        characterDetailPresenter.init()

        verify(detailView).init()
        verify(characterServicesImpl).getCharactersDetails(1)
        verify(detailView).showToast(errorMessage)
    }

    @Test
    fun responseWithItem(){

        var characters = CharactersFactory.getMockCharacter()
        var observable = Single.just(characters)

        getCharacterDetailsServiceUseCase.stub {
            on { getCharacterDetailsServiceUseCase.invoke(1) } doReturn (observable)
        }
        detailView.stub {
            on { detailView.characterId } doReturn 1
        }

        characterDetailPresenter.init()
        //getCharacterDetailsServiceUseCase.invoke(1)

        verify(detailView).init()
        verify(characterServicesImpl).getCharactersDetails(1)
        verify(detailView).showCharacterDetals(characters[0])

    }

    @Test
    fun responseWithOutItem(){

        var characters = emptyList<Character>()
        var observable = Single.just(characters)

        getCharacterDetailsServiceUseCase.stub {
            on { getCharacterDetailsServiceUseCase.invoke(1) } doReturn (observable)
        }
        detailView.stub {
            on { detailView.characterId } doReturn 1
        }

        characterDetailPresenter.init()

        verify(detailView).init()
        verify(characterServicesImpl).getCharactersDetails(1)
        verify(detailView).showToastNoItemToShow()

    }

}