package com.lu.plate.recycler.data

import androidx.annotation.Keep

@Keep
class PlateStructure(
    var layoutType: Int = LAYOUT_TYPE_RECYCLER,
    var timeStamp: Long = 0,
    var version: Int = 0,
    var components: MutableList<ComponentModel> = arrayListOf<ComponentModel>(),
) {
    companion object {
        val LAYOUT_TYPE_RECYCLER = 0
        val LAYOUT_TYPE_LIST_VIEW = 1
        val LAYOUT_TYPE_SCROLL = 2
    }
}