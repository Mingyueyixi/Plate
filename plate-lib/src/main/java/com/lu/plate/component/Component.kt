package com.lu.plate.component

import com.lu.plate.recycler.data.ContentWrap


interface Component {
    fun refresh(data: ContentWrap)
}

interface LifeComponent{
    fun onCreate()
    fun onMounted()
    fun unMounted()
}

