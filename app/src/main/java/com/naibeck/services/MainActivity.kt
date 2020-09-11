package com.naibeck.services

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.naibeck.services.TimeCounterService.Companion.COUNTER
import com.naibeck.services.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("Hola")
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.foregroundServiceButton.setOnClickListener { launchService(Services.ForegroundService, binding.messageToService.text.toString().toInt()) }
        binding.intentServiceButton.setOnClickListener { launchService(Services.IntentService, binding.messageToService.text.toString().toInt()) }
        binding.stopForegroundButton.setOnClickListener { stopForegroundService() }
    }

    private fun stopForegroundService() {
        val intent = Intent(this, TimeCounterService::class.java)
        stopService(intent)
    }

    private fun launchService(serviceType: Services, value: Int) {
        when (serviceType) {
            is Services.ForegroundService -> launchForegroundService(value)
            is Services.IntentService -> launchIntentService(value)
        }
    }

    private fun launchIntentService(value: Int) {
        TaskCompleterIntentService.enqueueMethod(this, Intent(this, TaskCompleterIntentService::class.java))
    }

    private fun launchForegroundService(value: Int) {
        val intent = Intent(this, TimeCounterService::class.java)
        intent.putExtra(COUNTER, value)
        ContextCompat.startForegroundService(this, intent)
    }
}

sealed class Services {
    object ForegroundService : Services()
    object IntentService : Services()
}