package com.kunal.uptodo.views

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    abstract fun handleBack()
    abstract fun pageType() : String

}