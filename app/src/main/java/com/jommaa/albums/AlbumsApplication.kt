package com.jommaa.albums

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AlbumsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}