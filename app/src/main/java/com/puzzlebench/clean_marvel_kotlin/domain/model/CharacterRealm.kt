package com.puzzlebench.clean_marvel_kotlin.domain.model

import com.puzzlebench.clean_marvel_kotlin.Utils.Constant
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class CharacterRealm(
        @PrimaryKey var id: Int = Constant.DEFAULT_INT_VALUE,
        var name: String = Constant.EMPTY_STRING,
        var description: String = Constant.EMPTY_STRING,
        var thumbnail: ThumbnailRealm? = null
): RealmObject()
