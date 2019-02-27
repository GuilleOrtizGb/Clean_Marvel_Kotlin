package com.puzzlebench.clean_marvel_kotlin.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.Utils.DebouncedOnClickListener
import com.puzzlebench.clean_marvel_kotlin.data.database.ChatacterDataRepoImplementation
import com.puzzlebench.clean_marvel_kotlin.data.service.CharacterServicesImpl
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
//    @Inject
//    lateinit var getCharactersSaveUseCase: GetCharactersSaveUseCase

    @Inject @field:Named("Love")
    lateinit var infoLove: Info

    @Inject @field:Named("Hate")
    lateinit var infoHate: Info

    val getCharacterServiceUseCase = GetCharacterServiceUseCase(CharacterServicesImpl())
    val getCharacterSaveUseCase = GetCharactersSaveUseCase(ChatacterDataRepoImplementation());
   // val getCharacterSaveUseCase = getCharactersSaveUseCase
//    val getCharacterSaveUseCase = GetCharactersSaveUseCase(characterServiceUseCase);
    val presenter = CharacterPresenter(CharecterView(this), getCharacterServiceUseCase,
            getCharacterSaveUseCase, subscriptions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        DaggerChatacterDataRepoComponent.create().inject(this)
        DaggerMagicBox.create().inject(this)
        Toast.makeText(this,infoLove.text,Toast.LENGTH_LONG)

        presenter.init()

        this.floatingActionButton.setOnClickListener(DebouncedOnClickListener(View.OnClickListener {
            presenter.fabListener()
        }))
    }
}

@Module
class Bag{
    @Provides
    @Named("Love")
    fun sayLoveDagger2(): Info{
        return Info("Love Dagger 2")
    }
    @Provides
    @Named("Hate")
    fun sayHateDagger2(): Info{
        return Info("Hate Dagger 2")
    }
}

class Info(val text: String)

@Component(modules = [Bag::class])
interface MagicBox {
    //I also need to tell my magic box, it is there to perform it’s
    // magic on MainActivity. So to do that, I create a poke function
    // accepting MainActivity in my MagicBox.
    fun inject(app: MainActivity)//inject in this class()
}