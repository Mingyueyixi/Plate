package com.lu.plate.data

import androidx.annotation.Keep

@Keep
abstract class ArchiveSource<T> {
    var __archive__: T? = null
        get() {
            if (field == null) {
                field = onLazyInitArchiveValue()
            }
            return field
        }
        set(value) {
            field = value
        }

    abstract fun onLazyInitArchiveValue(): T
}