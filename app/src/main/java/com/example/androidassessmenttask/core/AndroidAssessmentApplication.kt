package com.example.androidassessmenttask.core

import android.app.Application
import com.example.androidassessmenttask.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AndroidAssessmentApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AndroidAssessmentApplication)
            modules(koinModules)
        }
    }
}