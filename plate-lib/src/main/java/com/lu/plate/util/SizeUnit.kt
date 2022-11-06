package com.lu.plate.util

import android.util.TypedValue
import com.lu.plate.Plate

class SizeUnit {
    companion object {
        const val dp = "dp"
        const val sp = "sp"
        const val px = "px"
        const val res = "res"

        @JvmStatic
        fun getPixel(unit: String, size: Int): Int {
            return when (unit) {
                dp -> {
                    TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        size.toFloat(),
                        Plate.context.resources.displayMetrics
                    ).toInt()
                }
                sp -> {
                    TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_SP,
                        size.toFloat(),
                        Plate.context.resources.displayMetrics
                    ).toInt()
                }
                else -> size
            }
        }

        @JvmStatic
        fun getPixelFloat(unit: String, size: Float): Float {
            return when (unit) {
                dp -> {
                    TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        size,
                        Plate.context.resources.displayMetrics
                    )
                }
                sp -> {
                    TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_SP,
                        size.toFloat(),
                        Plate.context.resources.displayMetrics
                    )
                }
                else -> size
            }
        }
    }
}