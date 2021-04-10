package com.baldeagles.raksha.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.baldeagles.raksha.data.repo.SafeHouseRepo
import com.baldeagles.raksha.data.repo.SafeHouseRepoImpl
import com.vaibhav.nextlife.utils.location.LocationLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: SafeHouseRepoImpl,
    private val context: Application
) : AndroidViewModel(context) {

    private val safeHouses = repo.getAllSafeHouse()

    private val _location = LocationLiveData(context)


}