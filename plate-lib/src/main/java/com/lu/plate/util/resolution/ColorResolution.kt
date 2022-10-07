package com.lu.plate.util.resolution

import android.graphics.Color

class ColorResolution {
    fun parseColor(colorText: String?, defaultColor: Int = Color.TRANSPARENT): Int {
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