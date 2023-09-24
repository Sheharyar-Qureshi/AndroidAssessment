package com.example.androidassessmenttask.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class AndroidAssessmentBaseActivity<DB : ViewDataBinding> : AppCompatActivity() {
    lateinit var binding: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = DataBindingUtil.setContentView(this, getLayout())
        setObservers()
        setupViews()
        setListeners()
    }

    abstract fun getLayout(): Int
    abstract fun setupViews()
    abstract fun setListeners()
    abstract fun setObservers()
}