package com.lu.plate.demo.util.log

import android.util.Log

class LogUtil {

    companion object {
        const val TAG = ">>>"

        @JvmStatic
        fun log(vararg msg: Any?) {
            Log.i(TAG, joinMsg(msg))
        }

        @JvmStatic
        fun i(vararg msg: Any?) {
            Log.i(TAG, joinMsg(msg))
        }

        @JvmStatic
        fun d(vararg msg: Any?) {
            Log.d(TAG, joinMsg(msg))
        }

        @JvmStatic
        fun w(vararg msg: Any?) {
            Log.w(TAG, joinMsg(msg))
        }

        @JvmStatic
        fun e(vararg msg: Any?) {
            Log.e(TAG, joinMsg(msg))
        }

        @JvmStatic
        fun joinMsg(msg: Array<out Any?>): String {
            var sb = StringBuilder()
            msg.forEachIndexed { index, ele ->
                if (ele is Throwable) {
                    sb.append(ele.stackTraceToString())
                } else {
                    sb.append(ele.toString())
                }
                if (index != msg.size - 1) {
                    sb.append("  ")
                }
            }
            return sb.toString()
        }
    }
} 