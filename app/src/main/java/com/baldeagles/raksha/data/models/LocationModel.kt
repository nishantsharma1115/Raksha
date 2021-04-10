package com.baldeagles.raksha.data.models

data class LocationModel(
    var lat: Double = 0.0,
    var long: Double = 0.0,
    var address: String = "",
    var locality: String = "",
    var city: String = "",
    var state: String = "",
    var country: String = ""
)