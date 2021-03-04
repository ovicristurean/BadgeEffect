package com.ovidiucristurean.app_core

import android.app.Application

class CoreApp : Application() {

    override fun onCreate() {
        super.onCreate()
        coreApp = this
    }

    companion object {

        private lateinit var coreApp: CoreApp

        fun get(): CoreApp {
            return coreApp
        }
    }
}