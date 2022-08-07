package com.lu.plate.util

import android.graphics.Color

class ColorUtil {
    companion object {
        @JvmStatic
        fun parseColor(colorText: String?, defaultColor: Int = Color.TRANSPARENT): Int {
            if (colorText == null) {
                return defaultColor
            }
            return try {
                Color.parseColor(colorText)
            } catch (e: Exception) {
                defaultColor
            }
        }
    }
}