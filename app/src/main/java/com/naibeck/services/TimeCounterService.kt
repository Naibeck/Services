package com.naibeck.services

import android.app.Service
import android.content.Intent
import android.os.IBinder

class TimeCounterService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}