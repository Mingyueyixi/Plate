package com.lu.plate.recycler.data

import androidx.annotation.Keep

@Keep
class ComponentModel(
    val componentId: Int = -1,
    var style: Style? = null,
    var contents: MutableList<ContentModel> = mutableListOf()
) {

}