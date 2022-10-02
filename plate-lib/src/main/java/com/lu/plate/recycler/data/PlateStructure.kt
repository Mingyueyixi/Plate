package com.lu.plate.recycler.data

import androidx.annotation.Keep

@Keep
class PlateStructure(
    var cType: Int = CONTINER_TYPE_RECYCLER,
    var timeStamp: Long = 0,
    var version: Int = 0,
    var contents: MutableList<ContentWrap> = arrayListOf<ContentWrap>(),
) {
    companion object {
        val CONTINER_TYPE_RECYCLER = 0
        val CONTINER_TYPE_LIST_VIEW = 1
        val CONTINER_TYPE_SCROLL = 2
    }

}
