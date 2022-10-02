package com.lu.plate.data

import androidx.annotation.Keep

@Keep
class PlateStructure(
    var type: Int = TYPE_RECYCLER,
    var timeStamp: Long = 0,
    var version: Int = 0,
    var contents: MutableList<Content> = arrayListOf<Content>(),
) {
    companion object {
        val TYPE_RECYCLER = 0
        val TYPE_LIST_VIEW = 1
        val TYPE_SCROLL = 2
    }

}
