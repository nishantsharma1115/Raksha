package com.baldeagles.raksha.data.models

data class LocationModel(
    val lat: Double = 0.0,
    val long: Double = 0.0,
    val address: String = "",
    val locality: String = "",
    val city: String = "",
    val state: String = "",
    val country: String = ""
)