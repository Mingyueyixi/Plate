package com.lu.plate.data

import androidx.annotation.Keep
import com.lu.plate.data.drawable.ShapeBackground

@Keep
class Style(
    var width: String? = null,
    var height: String? = null,
    var marginLeft: String? = null,
    var marginRight: String? = null,
    var marginTop: String? = null,
    var marginBottom: String? = null,
    var paddingLeft: String? = null,
    var paddingRight: String? = null,
    var paddingTop: String? = null,
    var paddingBottom: String? = null,
    var background: ShapeBackground? = null
)