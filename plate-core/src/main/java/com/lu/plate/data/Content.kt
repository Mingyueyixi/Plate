package com.lu.plate.data

import androidx.annotation.Keep
import org.json.JSONObject

@Keep
class Content(
    val templateId: Int = -1,
    var style: Style? = null,
    var elements: MutableList<Element<JSONObject>> = mutableListOf()
)