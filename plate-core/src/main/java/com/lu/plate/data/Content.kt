package com.lu.plate.data

import androidx.annotation.Keep
import org.json.JSONObject

@Keep
class Content(
    /** 内容使用的视图模板 */
    val templateId: Int = -1,
    /** 样式 */
    style: Style? = null,
    /** content作为列表视图时的子元素集合，普通视图不需要 */
    var elements: MutableList<Element>? = null,
    /** content上的非公共的描述数据；不同的template，样式、数据存在不同 */
    props: JSONObject? = null
) : Element(style = style, props = props)