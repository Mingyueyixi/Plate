package com.lu.plate.util.resolve

import android.graphics.Color

open class ColorResolve {
    open fun parseColor(colorText: String?, defaultColor: Int = Color.TRANSPARENT): Int {
        if (colorText == null) {
            return defaultColor
        }
        return try {
            Color.parseColor(colorText)
        } catch (e: Exception) {
            e.printStackTrace()
            defaultColor
        }
    }
}