package com.lu.plate.util.resolve

import android.graphics.PorterDuff
import android.graphics.drawable.GradientDrawable
import android.os.Build
import androidx.annotation.RequiresApi
import com.lu.plate.data.drawable.ShapeBackground

class BackgroundResolve {

    open fun create(sb: ShapeBackground): GradientDrawable {
        val drawable = GradientDrawable()
        applyNodeAttr(sb, drawable)
        applyChildNodeStroke(sb, drawable)
        applyChildNodeSize(sb, drawable)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            applyChildNodePadding(sb, drawable)
        }
        applyChildNodeCorners(sb, drawable)
        applyChildNodeGradient(sb, drawable)

        applyChildNodeSolid(sb, drawable)
        return drawable
    }

    private fun applyChildNodeGradient(sb: ShapeBackground, drawable: GradientDrawable) {

        sb.gradient?.let {

            it.angle?.let { aIt ->
                val angle = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    (aIt % 360 + 360) % 360 // offset negative angle measures
                } else {
                    aIt % 360
                }
                if (angle >= 0) {
                    when (angle) {
                        0f -> GradientDrawable.Orientation.LEFT_RIGHT
                        45f -> drawable.orientation = GradientDrawable.Orientation.BL_TR
                        90f -> drawable.orientation = GradientDrawable.Orientation.BOTTOM_TOP
                        135f -> drawable.orientation = GradientDrawable.Orientation.BR_TL
                        180f -> drawable.orientation = GradientDrawable.Orientation.RIGHT_LEFT
                        225f -> drawable.orientation = GradientDrawable.Orientation.TR_BL
                        270f -> drawable.orientation = GradientDrawable.Orientation.TOP_BOTTOM
                        315f -> drawable.orientation = GradientDrawable.Orientation.TL_BR
                        else -> {
                            println("error!!")
                        }
                    }
                } else {
                    drawable.orientation = GradientDrawable.Orientation.TOP_BOTTOM
                }
            }

            drawable.setGradientCenter(it.centerX ?: 0.5f, it.centerY ?: 0.5f)
            it.startColor?.let {

            }

            val colors = arrayListOf<Int>(
                StyleComposite.color.parseColor(it.startColor),
                StyleComposite.color.parseColor(it.endColor),
            )
            if (it.centerColor != null) {
                colors.add(StyleComposite.color.parseColor(it.centerColor))
            }
            drawable.colors = colors.toIntArray()

        }


    }

    private fun applyChildNodeCorners(sb: ShapeBackground, drawable: GradientDrawable) {
        if (sb.corners == null) {
            return
        }
        val radius = sb.corners?.radius
        if (radius != null) {
            drawable.cornerRadius = StyleComposite.size.parseFloat(radius)
        } else {
            val tlr = StyleComposite.size.parseFloat(sb.corners?.topLeftRadius)
            val trr = StyleComposite.size.parseFloat(sb.corners?.topRightRadius)
            val brr = StyleComposite.size.parseFloat(sb.corners?.bottomRightRadius)
            val blr = StyleComposite.size.parseFloat(sb.corners?.bottomLeftRadius)
            //top-left, top-right, bottom-right, bottom-left
            drawable.cornerRadii = floatArrayOf(tlr, tlr, trr, trr, brr, brr, blr, blr)
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun applyChildNodePadding(sb: ShapeBackground, drawable: GradientDrawable) {
        val l = StyleComposite.size.parseInt(sb.padding?.left)
        val t = StyleComposite.size.parseInt(sb.padding?.top)
        val r = StyleComposite.size.parseInt(sb.padding?.right)
        val b = StyleComposite.size.parseInt(sb.padding?.bottom)
        drawable.setPadding(l, t, r, b)
    }

    private fun applyChildNodeSize(sb: ShapeBackground, drawable: GradientDrawable) {
        val width = StyleComposite.size.parseIntOrNull(sb.size?.width)
        val height = StyleComposite.size.parseIntOrNull(sb.size?.height)
        if (width != null && height != null) {
            drawable.setSize(width, height)
        }
    }

    private fun applyChildNodeStroke(sb: ShapeBackground, drawable: GradientDrawable) {
        sb.stroke?.let {
            val width = StyleComposite.size.parseInt(it.width)
            val color = StyleComposite.color.parseColor(it.color)
            val dashWidth = StyleComposite.size.parseFloat(it.dashWidth)
            val dashGap = StyleComposite.size.parseFloat(it.dashGap)
            drawable.setStroke(width, color, dashWidth, dashGap)
        }
    }

    private fun applyChildNodeSolid(sb: ShapeBackground, drawable: GradientDrawable) {
        sb.solid?.let {
            drawable.setColor(StyleComposite.color.parseColor(it.color))
        }
    }

    private fun applyNodeAttr(sb: ShapeBackground, drawable: GradientDrawable) {
        when (sb.shape) {
            "rectangle" -> {
                drawable.shape = GradientDrawable.RECTANGLE
            }
            "line" -> {
                drawable.shape = GradientDrawable.LINE
            }
            "ring" -> {
                drawable.shape = GradientDrawable.RING
            }
            else -> {

            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            StyleComposite.size.parseIntOrNull(sb.innerRadius)?.let {
                drawable.innerRadius = it
            }
            sb.innerRadiusRatio?.let {
                drawable.innerRadiusRatio = it
            }
            StyleComposite.size.parseIntOrNull(sb.thickness)?.let {
                drawable.thickness = it
            }
            sb.thicknessRatio?.let {
                drawable.thicknessRatio = it
            }
        }
        sb.dither?.let {
            drawable.setDither(it)
        }
        sb.tint?.let {
            drawable.setTint(StyleComposite.color.parseColor(it))
        }

        when (sb.tintMode) {
            "src_over" -> drawable.setTintMode(PorterDuff.Mode.SRC_OVER)
            "src_in" -> drawable.setTintMode(PorterDuff.Mode.SRC_IN)
            "src_atop" -> drawable.setTintMode(PorterDuff.Mode.SRC_ATOP)
            "multiply" -> drawable.setTintMode(PorterDuff.Mode.MULTIPLY)
            "screen" -> drawable.setTintMode(PorterDuff.Mode.SCREEN)
            "add" -> drawable.setTintMode(PorterDuff.Mode.ADD)
        }
        sb.useLevel?.let {
            drawable.useLevel = it
        }
        sb.visible?.let {
            drawable.setVisible(it, true)
        }
    }


}