package com.baldeagles.raksha.util

import android.content.Context
import android.location.Address
import android.location.Geocoder
import com.baldeagles.raksha.data.models.LocationModel
import com.google.android.gms.common.util.CollectionUtils
import java.util.*

object GeoCoderUtil {

    fun execute(
        context: Context,
        latitude: String,
        longitude: String,
        callback: LoadDataCallback<LocationModel>
    ) {
        var currentLatitude = 0.0
        var currentLongitude = 0.0
        var currentAddress = ""
        var currentLocality = ""
        var currentCity = ""
        var currentState = ""
        var currentCountry = ""
        val locationModel: LocationModel
        try {
            val addresses: MutableList<Address>
            val geocoder = Geocoder(context, Locale.ENGLISH)
            addresses =
                geocoder.getFromLocation(latitude.toDouble(), longitude.toDouble(), 1)
            if (!CollectionUtils.isEmpty(addresses)) {
                val fetchedAddress = addresses[0]
                if (fetchedAddress.maxAddressLineIndex > -1) {
                    currentAddress = fetchedAddress.getAddressLine(0)
                    currentLatitude = fetchedAddress.latitude
                    currentLongitude = fetchedAddress.longitude
                    currentLocality = fetchedAddress.featureName
                    currentCity = fetchedAddress.locality
                    currentState = fetchedAddress.adminArea
                    currentCountry = fetchedAddress.countryName
                }
                locationModel = LocationModel().apply {
                    this.lat = currentLatitude
                    this.long = currentLongitude
                    this.address = currentAddress
                    this.city = currentCity
                    this.country = currentCountry
                    this.locality = currentLocality
                    this.state = currentState
                }
                callback.onDataLoaded(locationModel)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            callback.onDataNotAvailable(1, "Something went wrong!")
        }
    }
}

interface LoadDataCallback<T> {
    fun onDataLoaded(response: T) {
    }

    fun onDataNotAvailable(errorCode: Int, reasonMsg: String) {

    }
}