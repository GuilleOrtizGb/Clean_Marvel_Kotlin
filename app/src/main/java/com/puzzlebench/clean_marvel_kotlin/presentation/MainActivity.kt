package com.puzzlebench.clean_marvel_kotlin.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.puzzlebench.clean_marvel_kotlin.MainApplication
import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.Utils.DebouncedOnClickListener
import com.puzzlebench.clean_marvel_kotlin.data.database.ChatacterDataRepoImplementation
import com.puzzlebench.clean_marvel_kotlin.data.service.CharacterServicesImpl
import com.puzzlebench.clean_marvel_kotlin.di.DaggerCharacterSaveUseCaseComponet
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharactersSaveUseCase
import com.puzzlebench.clean_marvel_kotlin.presentation.base.BaseRxActivity
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.CharacterPresenter
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.CharecterView
import dagger.Component
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_main.*
import java.time.Duration
import javax.inject.Inject
import javax.inject.Named

open class MainActivity : BaseRxActivity() {
    @Inject
    lateinit var getCharactersSaveUseCase: GetCharactersSaveUseCase

//    @Inject @field:Named("Love")
//    lateinit var infoLove: Info
//
//    @Inject @field:Named("Hate")
//    lateinit var infoHate: Info



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MainApplication.characterSaveUseCaseComponet.inject(this)

        //DaggerCharacterSaveUseCaseComponet.create().inject(this)

        // DaggerMagicBox.create().inject(this)
        // Toast.makeText(this,infoLove.text,Toast.LENGTH_LONG)

        val getCharacterServiceUseCase = GetCharacterServiceUseCase(CharacterServicesImpl())
        //val getCharacterSaveUseCase = GetCharactersSaveUseCase(ChatacterDataRepoImplementation());
        //val getCharacterSaveUseCase = getCharactersSaveUseCase
        // val getCharacterSaveUseCase = GetCharactersSaveUseCase(characterServiceUseCase);
        val presenter = CharacterPresenter(CharecterView(this), getCharacterServiceUseCase,
                getCharactersSaveUseCase, subscriptions)

        presenter.init()

        this.floatingActionButton.setOnClickListener(DebouncedOnClickListener(View.OnClickListener {
            presenter.fabListener()
        }))
    }
}

//@Module
//class Bag{
//    @Provides
//    @Named("Love")
//    fun sayLoveDagger2(): Info{
//        return Info("Love Dagger 2")
//    }
//    @Provides
//    @Named("Hate")
//    fun sayHateDagger2(): Info{
//        return Info("Hate Dagger 2")
//    }
//}
//
//class Info(val text: String)
//
//@Component(modules = [Bag::class])
//interface MagicBox {
//    fun inject(app: MainActivity)//inject in this class()
//}