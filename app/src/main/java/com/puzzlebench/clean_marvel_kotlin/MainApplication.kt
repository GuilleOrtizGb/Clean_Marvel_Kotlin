package com.puzzlebench.clean_marvel_kotlin

import android.app.Application
import android.content.Context
import com.puzzlebench.clean_marvel_kotlin.di.CharacterSaveUseCaseComponet
import com.puzzlebench.clean_marvel_kotlin.di.CharactersSaveUseCaseModule
import com.puzzlebench.clean_marvel_kotlin.di.DaggerCharacterSaveUseCaseComponet
import io.realm.Realm
import io.realm.RealmConfiguration

class MainApplication : Application() {

    companion object {
        lateinit var characterSaveUseCaseComponet: CharacterSaveUseCaseComponet
    }

    override fun onCreate() {
        super.onCreate()

        initRealm()
        initDagger()
    }

    private fun initDagger() {
        characterSaveUseCaseComponet = DaggerCharacterSaveUseCaseComponet.builder()
                .charactersSaveUseCaseModule(CharactersSaveUseCaseModule())
                .build()
    }

    private fun initRealm() {
        Realm.init(this);

        val config = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()

        Realm.setDefaultConfiguration(config)
    }
}