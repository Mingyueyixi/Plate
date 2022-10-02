package com.lu.plate.component

import com.lu.plate.recycler.data.Content


interface Component {
    fun refresh(data: Content)
}
