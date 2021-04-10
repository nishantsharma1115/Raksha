package com.vaibhav.nextlife.utils.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.lifecycle.LiveData
import com.baldeagles.raksha.data.models.LocationModel
import com.baldeagles.raksha.utils.Resource
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import java.util.*


class LocationLiveData(private val context: Context) : LiveData<Resource<LocationModel>>() {

    private var fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    override fun onInactive() {
        super.onInactive()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    fun postLoading() {
        value = Resource.Loading()
    }

    fun postError() {
        value = Resource.Error("Error fetching location", data = null)
    }

    override fun onActive() {
        super.onActive()
    }

    @SuppressLint("MissingPermission")
    fun startListening() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.also {
                    setLocationData(it)
                }
            }
        startLocationUpdates()
    }

    fun stopListening() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            null
        )
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult ?: return
            for (location in locationResult.locations) {
                setLocationData(location)
            }
        }
    }

    private fun setLocationData(location: Location) {
        val geocoder: Geocoder = Geocoder(context, Locale.getDefault())

        val addresses: List<Address> = geocoder.getFromLocation(
            location.latitude,
            location.longitude,
            1
        ) // Here 1 represent max location result to returned, by documents it recommended 1 to 5


        val address: String =
            addresses[0].getAddressLine(0) // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

        val city: String = addresses[0].locality ?: ""
        val state: String = addresses[0].adminArea ?: ""
        val country: String = addresses[0].countryName ?: ""
        val postalCode: String = addresses[0].postalCode ?: ""
        val knownName: String = addresses[0].featureName ?: ""// Only if available else return NULL

        value = Resource.Success(
            LocationModel(
                long = location.longitude,
                lat = location.latitude,
                address = address,
                city = city,
                state = state,
                country = country,
                locality = knownName
            )
        )
    }

    companion object {
        val locationRequest: LocationRequest = LocationRequest.create().apply {
            interval = 600000L
            fastestInterval = 500000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }
}

