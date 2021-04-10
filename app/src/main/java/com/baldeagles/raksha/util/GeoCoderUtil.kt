package com.baldeagles.raksha.util

import android.content.Context
import android.location.Address
import android.location.Geocoder
import com.baldeagles.raksha.data.models.LocationModelForMap
import com.google.android.gms.common.util.CollectionUtils
import java.util.*

object GeoCoderUtil {

    fun execute(
        context: Context,
        latitude: String,
        longitude: String,
        callback: LoadDataCallback<LocationModelForMap>
    ) {
        var address = ""
        var cityName = ""
        var areaName = ""
        val locationModelForMap: LocationModelForMap
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
                locationModelForMap = LocationModelForMap().apply {
                    locationAddress = address
                    locationCityName = cityName
                    locationAreaName = areaName
                }
                callback.onDataLoaded(locationModelForMap)
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