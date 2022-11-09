package com.lu.plate.data

import androidx.annotation.Keep
import com.google.gson.JsonObject
import org.json.JSONObject

@Keep
open class Element(
    /** 元素的id，用于区分元素，一般使用不到 */
    var id: Int = 0,
    /** 元素的样式 */
    var style: Style? = null,
    /** element上的其他描述数据 */
    var props: JsonObject? = null
)