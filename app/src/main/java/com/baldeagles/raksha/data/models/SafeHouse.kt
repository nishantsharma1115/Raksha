package com.baldeagles.raksha.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SafeHouse(
    var name: String = "",
    var lat: Double = 0.0,
    var lon: Double = 0.0,
    var address: String = "",
    @PrimaryKey
    val timeStamp: String = System.currentTimeMillis().toString()

)
