package com.example.scholarfleet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.WindowManager.*
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import android.widget.Toast
import android.window.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen


class SplashLayout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val SplashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_layout)


        SplashScreen.setKeepOnScreenCondition{ true }
        Handler().postDelayed({

            val envio = Intent(this@SplashLayout, HomeActivity::class.java)
            startActivity(envio)
            finish()
        }, 5000)
    }
}