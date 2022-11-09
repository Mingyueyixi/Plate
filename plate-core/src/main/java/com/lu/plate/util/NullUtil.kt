package com.lu.plate.util

import com.google.gson.JsonObject

class NullUtil {
    companion object {

        @JvmStatic
        fun hasNull(vararg params: Any?): Boolean {
            for (p in params) {
                if (p == null) {
                    return true
                }
            }
            return false
        }

        @JvmStatic
        fun isNotNull(vararg params: Any?): Boolean {
            return !hasNull(params)
        }
    }
}