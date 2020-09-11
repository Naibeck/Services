package com.naibeck.services

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.app.NotificationManagerCompat
import timber.log.Timber

class ServicesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID,
                SERVICE_CHANNEL,
                NotificationManager.IMPORTANCE_DEFAULT)
            NotificationManagerCompat.from(this).createNotificationChannel(channel)
        }
    }

    companion object {
        const val CHANNEL_ID = "services.udb"
        const val SERVICE_CHANNEL = "Services UDB"
    }
}