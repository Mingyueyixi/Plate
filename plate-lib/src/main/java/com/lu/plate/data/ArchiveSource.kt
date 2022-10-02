package com.lu.plate.data

import androidx.annotation.Keep

@Keep
abstract class ArchiveSource<T> {
    var __archive__: T? = null
        get() {
            if (field == null) {
                return __fetchArchive()
            }
            return field
        }
        set(value) {
            field = value
        }

    abstract fun __fetchArchive(): T
}