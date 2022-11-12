package com.lu.plate.demo.base

import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    @Suppress("DEPRECATION")
    override fun onBackPressed() {
        super.onBackPressed()
        finishAfterTransition()
    }
}