package com.lu.plate.demo

import android.app.Application

class App : Application() {
    companion object {
        @JvmStatic
        val INSTANCE: App
            get() = sInstance

        @JvmStatic
        private lateinit var sInstance: App
    }

    override fun onCreate() {
        sInstance = this
        super.onCreate()
    }
}