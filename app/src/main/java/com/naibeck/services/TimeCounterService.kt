package com.naibeck.services

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.naibeck.services.ServicesApplication.Companion.CHANNEL_ID
import timber.log.Timber

class TimeCounterService : Service() {
    override fun onCreate() {
        super.onCreate()
        Timber.d("Service created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Timber.d("Service started command")
        val value: Int = intent?.getIntExtra(COUNTER, 0) ?: 0
        startForeground(FOREGROUND_ID, createNotification(value))

        return START_NOT_STICKY
    }

    private fun createNotification(value: Int?): Notification? {
        val intent = Intent(this, MainActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(this, FOREGROUND_REQUEST, intent, 0)

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Foreground Service")
            .setContentText("This service contains $value. It will last until user terminates it.")
            .setSmallIcon(R.drawable.ic_android)
            .setContentIntent(pendingIntent)
            .build()
        return notification
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("Service destroyed")
    }

    companion object {
        const val COUNTER = "counter.key"
        const val FOREGROUND_REQUEST = 0
        const val FOREGROUND_ID = 1
    }
}