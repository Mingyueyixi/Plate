package com.lu.plate.util.resolution

import android.view.ViewGroup
import com.lu.plate.PlateManager
import com.lu.plate.util.SizeUtil

class SizeResolution {
    companion object {
        const val WRAP_CONTENT = "wrap_content"
        const val MATCH_PARENT = "match_parent"
        const val UNIT_DP = "dp"
        const val UNIT_PX = "px"
        const val UNIT_SP = "sp"
    }

    fun resolve(textSource: String?): Int? {
        if (textSource.isNullOrBlank()) {
            return null
        }
        when (val text = textSource.trim()) {
            WRAP_CONTENT -> return ViewGroup.MarginLayoutParams.WRAP_CONTENT
            MATCH_PARENT -> return ViewGroup.MarginLayoutParams.MATCH_PARENT
            else -> {
                if (text.endsWith(UNIT_DP)) {
                    val numText = text.substring(0, text.length - UNIT_DP.length)
                    val num = numText.trim().toFloat()
                    return SizeUtil.dp2px(PlateManager.context.resources, num).toInt()
                } else if (text.endsWith(UNIT_SP)) {
                    val numText = text.substring(0, text.length - UNIT_SP.length)
                    val num = numText.trim().toFloat()
                    return SizeUtil.sp2px(PlateManager.context.resources, num).toInt()
                } else if (text.endsWith(UNIT_PX)) {
                    val numText = text.substring(0, text.length - UNIT_PX.length)
                    return numText.trim().toInt()
                } else {
                    //不带单位 --> 像素
                    //非法单位 --> exception --> warp
                    try {
                        return text.toInt()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
        return ViewGroup.MarginLayoutParams.WRAP_CONTENT
    }
}