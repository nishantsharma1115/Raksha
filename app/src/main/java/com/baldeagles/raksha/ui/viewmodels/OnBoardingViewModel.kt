package com.baldeagles.raksha.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baldeagles.raksha.data.repo.PreferencesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val preferencesRepo: PreferencesRepo) :
    ViewModel() {


    fun setOnBoardingComplete() {
        viewModelScope.launch {
            preferencesRepo.setOnBoardingComplete()
        }

    }
}