package com.lu.plate.data

import androidx.annotation.Keep

@Keep
class Element<T>(
    var id: Int = 0,
    var style: Style? = null,
    var data: MutableList<T> = mutableListOf()
)