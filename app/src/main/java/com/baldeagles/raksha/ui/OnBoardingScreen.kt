package com.baldeagles.raksha.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.baldeagles.raksha.R
import com.baldeagles.raksha.data.models.OnBoarding
import com.baldeagles.raksha.databinding.ActivityOnBoardingScreenBinding
import com.baldeagles.raksha.ui.adapters.OnBoardingAdapter

class OnBoardingScreen : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val onboardingList = mutableListOf<OnBoarding>()
        onboardingList.add(
            OnBoarding(
                R.drawable.onboarding2,
                getString(R.string.onboarding1),

                )
        )
        onboardingList.add(
            OnBoarding(
                R.drawable.onboarding3,
                getString(R.string.onboarding2),
                isLast = true
            )

        )
        val onBoardingAdapter = OnBoardingAdapter(onboardingList) {
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.onboardingViewpager.adapter = onBoardingAdapter
        binding.wormDotsIndicator.setViewPager2(binding.onboardingViewpager)
    }
}
