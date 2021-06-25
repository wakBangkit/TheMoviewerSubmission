package com.wak.jetpack.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.wak.jetpack.submission.R
import com.wak.jetpack.MainActivity

class SplashScreenActivity : AppCompatActivity() {
    companion object{
        private const val TIME_OUT_SPLASH = 2500L
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, TIME_OUT_SPLASH)
    }
}