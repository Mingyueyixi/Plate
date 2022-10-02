package com.lu.plate.recycler.data

import androidx.annotation.Keep

@Keep
class Element<T>(
    var id: Int = 0,
    var style: Style? = null,
    var data: MutableList<T> = mutableListOf()
)