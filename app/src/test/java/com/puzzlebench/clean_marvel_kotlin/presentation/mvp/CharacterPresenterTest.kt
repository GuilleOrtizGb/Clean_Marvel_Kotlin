package com.puzzlebench.clean_marvel_kotlin.presentation.mvp

import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.whenever
import com.puzzlebench.clean_marvel_kotlin.data.database.ChatacterDataRepoImplementation
import com.puzzlebench.clean_marvel_kotlin.data.service.CharacterServicesImpl
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterDetailsServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharactersSaveUseCase
import com.puzzlebench.clean_marvel_kotlin.mocks.factory.CharactersFactory
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

//TODO fix on second iteration
// error: However, there was exactly 1 interaction with this mock:
class CharacterPresenterTest {

    @Mock
    private lateinit var view: CharecterView
    @Mock
    private lateinit var characterServiceImp: CharacterServicesImpl
    @Mock
    private lateinit var characterSaveImp: ChatacterDataRepoImplementation
    private lateinit var characterPresenter: CharacterPresenter
    private lateinit var getCharacterServiceUseCase: GetCharacterServiceUseCase
    private lateinit var getCharacterSaveUseCase: GetCharactersSaveUseCase

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }

        getCharacterServiceUseCase = GetCharacterServiceUseCase(characterServiceImp)
        getCharacterSaveUseCase = GetCharactersSaveUseCase(characterSaveImp)
        val subscriptions = mock(CompositeDisposable::class.java)

        characterPresenter = CharacterPresenter(view, getCharacterServiceUseCase,
                getCharacterSaveUseCase, subscriptions)
    }

    @Test
    fun reposeWithError() {
        val itemsCharecters = CharactersFactory.getMockCharacter()
        val observable = Single.just(itemsCharecters)
        val observableSave = Completable.complete()

        Mockito.`when`(getCharacterServiceUseCase.invoke()).thenReturn(observable)
        Mockito.`when`(getCharacterSaveUseCase.invoke(itemsCharecters)).thenReturn(observableSave)

        characterPresenter.init()

        verify(view).init()
        verify(characterServiceImp).getCaracters()
        verify(view).showCharacters(itemsCharecters)
    }

    @Test
    fun reposeWithItemToShow() {
        val itemsCharecters = CharactersFactory.getMockCharacter()
        val observable = Single.just(itemsCharecters)
        val observableSave = Completable.complete()

        Mockito.`when`(getCharacterServiceUseCase.invoke()).thenReturn(observable)
        Mockito.`when`(getCharacterSaveUseCase.invoke(itemsCharecters)).thenReturn(observableSave)

        characterPresenter.init()

        verify(view).init()
        verify(characterServiceImp).getCaracters()
        verify(view).showCharacters(itemsCharecters)
    }

    @Test
    fun reposeWithoutItemToShow() {
        val itemsCharecters = emptyList<Character>()
        val observable = Single.just(itemsCharecters)

        Mockito.`when`(getCharacterServiceUseCase.invoke()).thenReturn(observable)

        characterPresenter.init()

        verify(view).init()
        verify(characterServiceImp).getCaracters()
        verify(view).showToastNoItemToShow()
        assertEquals(characterServiceImp.getCaracters(), observable)
    }
}