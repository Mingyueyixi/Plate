package com.lu.plate.util.resolution

class StyleResolve {

    companion object {
        var sizeResolution = SizeResolution()

        @JvmStatic
        fun parseSize(text: String?): Int? {
            return sizeResolution.resolve(text)
        }

    }


}