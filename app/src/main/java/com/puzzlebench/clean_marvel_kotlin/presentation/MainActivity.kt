package com.puzzlebench.clean_marvel_kotlin.presentation

import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.data.ContentProvider.CharactersContract
import com.puzzlebench.clean_marvel_kotlin.data.database.ChatacterDataPersistenceImplementation
import com.puzzlebench.clean_marvel_kotlin.data.service.CharacterServicesImpl
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharactersSaveUseCase
import com.puzzlebench.clean_marvel_kotlin.presentation.base.BaseRxActivity
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.showToast
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.CharacterPresenter
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.CharecterView
import io.realm.Realm
import io.realm.RealmConfiguration

open class MainActivity : BaseRxActivity() {

    val getCharacterServiceUseCase = GetCharacterServiceUseCase(CharacterServicesImpl())
    val getCharacterSaveUseCase = GetCharactersSaveUseCase(ChatacterDataPersistenceImplementation());
    val presenter = CharacterPresenter(CharecterView(this), getCharacterServiceUseCase,
            getCharacterSaveUseCase, subscriptions)

    // @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Realm.init(this);

        val config= RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()

        Realm.setDefaultConfiguration(config)

        presenter.init()


        // Provider test
        // var cursor: Cursor = contentResolver.query(CharactersContract.CONTENT_URI,null, null, null)
        // var cursorB: Cursor = contentResolver.query(CharactersContract.buildCharacterUriWithId(1009187),null, null, null)
        // applicationContext.showToast(cursor.getColumnName(1))

    }
}
