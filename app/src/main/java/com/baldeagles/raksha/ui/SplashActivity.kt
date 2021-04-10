package com.baldeagles.raksha.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import com.baldeagles.raksha.R
import com.baldeagles.raksha.ui.viewmodels.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val vieModel:SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            if(vieModel.isFirstTime)
                startActivity(Intent(this, OnBoardingScreen::class.java))
            else
                startActivity(Intent(this,MainActivity::class.java))
            finish()
        },2000L)

    }
}