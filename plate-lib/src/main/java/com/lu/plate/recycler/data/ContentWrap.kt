package com.lu.plate.recycler.data

import androidx.annotation.Keep

@Keep
class ContentWrap(
    val templateId: Int = -1,
    var style: Style? = null,
    var contents: MutableList<Content> = mutableListOf()
)