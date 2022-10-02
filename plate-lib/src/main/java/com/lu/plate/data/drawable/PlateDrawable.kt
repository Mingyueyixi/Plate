package com.lu.plate.data.drawable

import android.graphics.drawable.GradientDrawable
import com.lu.plate.util.ColorUtil
import com.lu.plate.util.SizeUnit
import com.lu.plate.util.StyleResolution

class ShapeBackground(
    var shape: String = "rectangle",
    var color: String = "#00000000",
    var strokeWidth: String? = null,
    var strokeColor: String? = null,
) {
    companion object {
        fun from(cb: ShapeBackground): GradientDrawable {
            val drawable = GradientDrawable()
            handleShape(drawable, cb)
            drawable.setColor(ColorUtil.parseColor(cb.color))
            handleStroke(drawable, cb)
            return drawable
        }

        private fun handleStroke(drawable: GradientDrawable, cb: ShapeBackground) {
            //描边
            StyleResolution.parseSize(cb.strokeWidth)?.let {
                drawable.setStroke(
                    it,
                    ColorUtil.parseColor(cb.strokeColor)
                )
            }
        }

        private fun handleShape(drawable: GradientDrawable, cb: ShapeBackground) {
            when (cb.shape) {
                "rectangle" -> {
                    drawable.shape = GradientDrawable.RECTANGLE
                }
                "line" -> {
                    drawable.shape = GradientDrawable.LINE
                }
                "ring" -> {
                    drawable.shape = GradientDrawable.RING
                }
            }
        }
    }
}