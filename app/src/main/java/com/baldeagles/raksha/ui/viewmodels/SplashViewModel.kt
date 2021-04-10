package com.baldeagles.raksha.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.baldeagles.raksha.data.repo.PreferencesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val preferencesRepo: PreferencesRepo) :
    ViewModel() {

    private val _isFirstTime = preferencesRepo.isFirstTime()
    val isFirstTime = _isFirstTime

}