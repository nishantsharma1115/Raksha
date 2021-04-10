package com.baldeagles.raksha.data.repo

import android.content.SharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PreferencesRepo @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun isFirstTime() =
        sharedPreferences.getBoolean(
            "isFirstTime",
            true
        )


    suspend fun setOnBoardingComplete() {
        withContext(Dispatchers.IO) {
            sharedPreferences.edit().putBoolean("isFirstTime", false).apply()
        }

    }
}