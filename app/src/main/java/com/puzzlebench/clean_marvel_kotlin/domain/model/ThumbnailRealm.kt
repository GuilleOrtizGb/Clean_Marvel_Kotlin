package com.puzzlebench.clean_marvel_kotlin.domain.model

import com.puzzlebench.clean_marvel_kotlin.Utils.Constant
import io.realm.RealmObject


open class ThumbnailRealm(
        var path: String = Constant.EMPTY_STRING,
        var extension: String= Constant.EMPTY_STRING
): RealmObject()