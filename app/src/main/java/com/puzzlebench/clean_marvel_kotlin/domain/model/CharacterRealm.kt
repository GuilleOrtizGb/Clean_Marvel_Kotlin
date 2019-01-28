package com.puzzlebench.clean_marvel_kotlin.domain.model

import io.realm.RealmObject

class CharacterRealm(var name: String? = null, var description: String? = null): RealmObject()
