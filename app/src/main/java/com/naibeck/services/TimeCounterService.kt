package com.naibeck.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import timber.log.Timber

class TimeCounterService : Service() {
    override fun onCreate() {
        super.onCreate()
        Timber.d("Service created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Timber.d("Service started command")
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("Service destroyed")
    }
}