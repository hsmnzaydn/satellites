package com.hsmnzaydn.satellites

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SatellitesApp:Application() {
    override fun onCreate() {
        super.onCreate()
    }
}