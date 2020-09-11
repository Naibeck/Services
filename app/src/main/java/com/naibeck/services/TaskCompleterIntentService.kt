package com.naibeck.services

import android.content.Context
import android.content.Intent
import android.os.SystemClock
import androidx.core.app.JobIntentService
import timber.log.Timber

class TaskCompleterIntentService : JobIntentService() {

    override fun onCreate() {
        super.onCreate()
        Timber.d("onCreate")
    }

    override fun onHandleWork(intent: Intent) {
        Timber.d("onHandleWork")
        for (i in 0 until 10) {
            Timber.d("Job current time is: $i")
            if (isStopped) return
            SystemClock.sleep(1000)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("onDestroy")
    }

    companion object {
        private const val JOB_ID = 1
        fun enqueueMethod(context: Context, intent: Intent) {
            enqueueWork(context, TaskCompleterIntentService::class.java, JOB_ID, intent)
        }
    }
}