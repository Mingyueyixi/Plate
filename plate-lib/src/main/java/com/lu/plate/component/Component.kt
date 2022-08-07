package com.lu.plate.component

import com.lu.plate.recycler.data.ComponentModel


interface Component {
    fun getComponentId(): Int
    fun refresh(data: ComponentModel)
}
