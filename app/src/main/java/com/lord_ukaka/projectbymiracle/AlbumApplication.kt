package com.lord_ukaka.projectbymiracle

import android.app.Application
import com.airbnb.lottie.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class AlbumApplication: Application() {

    //Load Timber on app creation
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}