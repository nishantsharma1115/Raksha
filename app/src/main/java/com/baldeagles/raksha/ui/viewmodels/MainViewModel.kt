package com.baldeagles.raksha.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.baldeagles.raksha.data.models.LocationModel
import com.baldeagles.raksha.data.models.SafeHouse
import com.baldeagles.raksha.data.repo.SafeHouseRepoImpl
import com.vaibhav.nextlife.utils.location.LocationLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: SafeHouseRepoImpl,
    private val context: Application
) : AndroidViewModel(context) {

    val safeHouses = repo.getAllSafeHouse()


    //for user location updates
    private val _location = LocationLiveData(context)
    val location = _location

    private val _safeHouseLocation = MutableLiveData<LocationModel>()
    val safeHouseLocation: LiveData<LocationModel> = _safeHouseLocation

    //to store complete address and location info of user's location
    private val _userLocation = MutableLiveData<LocationModel>(null)
    val userLocation: LiveData<LocationModel> = _userLocation

    //db related functions

    fun insertSafeHouse(safeHouse: SafeHouse) {
        viewModelScope.launch {
            repo.insertSafeHouse(safeHouse)
        }
    }

    fun deleteSafeHouse(safeHouse: SafeHouse) {
        viewModelScope.launch {
            repo.deleteSafeHouse(safeHouse)
        }
    }


    fun setLocation(locationModel: LocationModel) {
        _userLocation.postValue(locationModel)
    }


    fun setLocationForSafeHouse(locationModel: LocationModel) {
        _safeHouseLocation.postValue(locationModel)
    }


    fun startListeningToLocation() {
        _location.postLoading()
        try {
            _location.startListening()
        } catch (exception: Exception) {
            _location.postError()
        }

    }

    fun stopListeningToLocation() {
        _location.stopListening()
    }

}