package com.puzzlebench.clean_marvel_kotlin

import android.app.Application
import com.puzzlebench.clean_marvel_kotlin.di.AppComponent
import com.puzzlebench.clean_marvel_kotlin.di.AppModule
import com.puzzlebench.clean_marvel_kotlin.di.DaggerAppComponent
import io.realm.Realm
import io.realm.RealmConfiguration

class MainApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
        initRealm()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule())
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