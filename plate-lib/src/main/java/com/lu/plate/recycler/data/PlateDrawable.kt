package com.lu.plate.recycler.data

import android.graphics.drawable.GradientDrawable
import com.lu.plate.util.ColorUtil
import com.lu.plate.util.SizeUnit

class ShapeBackground(
    var shape: String = "rectangle",
    var color: String = "#00000000",
    var sizeUnit: String = "dp",
    var strokeWidth: Int? = null,
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
            cb.strokeWidth?.let {
                drawable.setStroke(
                    SizeUnit.getPixel(cb.sizeUnit, it).toInt(),
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