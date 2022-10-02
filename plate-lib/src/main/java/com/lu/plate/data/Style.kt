package com.lu.plate.data

import androidx.annotation.Keep

@Keep
class Style(
    var sizeUnit: String = "dp",
    var width: Int = -1,
    var height: Int = -1,
    var background: ShapeBackground? = null
)