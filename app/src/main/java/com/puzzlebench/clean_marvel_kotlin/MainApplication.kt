package com.puzzlebench.clean_marvel_kotlin

import android.app.Application
import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this);

        val config= RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()

        Realm.setDefaultConfiguration(config)
    }
}