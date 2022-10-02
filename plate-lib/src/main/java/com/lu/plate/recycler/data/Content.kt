package com.lu.plate.recycler.data

import androidx.annotation.Keep
import org.json.JSONObject

@Keep
class Content(
    var contentId: Int = 0,
    var style: Style?,
    var data: MutableList<JSONObject> = mutableListOf()
)