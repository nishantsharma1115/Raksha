package com.baldeagles.raksha.ui.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baldeagles.raksha.data.models.LocationModel
import com.baldeagles.raksha.util.GeoCoderUtil
import com.baldeagles.raksha.util.LoadDataCallback
import com.baldeagles.raksha.util.ResponseStatusCallbacks
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor() : ViewModel() {
    private val _getLocationInformation =
        MutableLiveData<ResponseStatusCallbacks<LocationModel>>()
    val getLocationInformationForMap: LiveData<ResponseStatusCallbacks<LocationModel>>
        get() = _getLocationInformation

    fun getLocationInfo(context: Context, latitude: String, longitude: String) {
        _getLocationInformation.value = ResponseStatusCallbacks.loading(data = null)
        GeoCoderUtil.execute(context, latitude, longitude, object :
            LoadDataCallback<LocationModel> {
            override fun onDataLoaded(response: LocationModel) {
                _getLocationInformation.value = ResponseStatusCallbacks.success(response)
            }

            override fun onDataNotAvailable(errorCode: Int, reasonMsg: String) {
                _getLocationInformation.value =
                    ResponseStatusCallbacks.error(data = null, message = "Something went wrong!")
            }
        })
    }
}