package com.baldeagles.raksha.util

import android.content.Context
import android.location.Address
import android.location.Geocoder
import com.baldeagles.raksha.ui.data.models.LocationModel
import com.google.android.gms.common.util.CollectionUtils
import java.util.*

object GeoCoderUtil {

    fun execute(
        context: Context,
        latitude: String,
        longitude: String,
        callback: LoadDataCallback<LocationModel>
    ) {
        var address = ""
        var cityName = ""
        var areaName = ""
        val locationModel: LocationModel
        try {
            val addresses: MutableList<Address>
            val geocoder = Geocoder(context, Locale.ENGLISH)
            addresses =
                geocoder.getFromLocation(latitude.toDouble(), longitude.toDouble(), 1)
            if (!CollectionUtils.isEmpty(addresses)) {
                val fetchedAddress = addresses[0]
                if (fetchedAddress.maxAddressLineIndex > -1) {
                    address = fetchedAddress.getAddressLine(0)
                    fetchedAddress.locality?.let {
                        cityName = it
                    }
                    fetchedAddress.subLocality?.let {
                        areaName = it
                    }
                }
                locationModel = LocationModel().apply {
                    locationAddress = address
                    locationCityName = cityName
                    locationAreaName = areaName
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