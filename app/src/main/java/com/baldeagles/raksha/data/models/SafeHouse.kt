package com.baldeagles.raksha.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SafeHouse(
    @PrimaryKey(autoGenerate = true)
    val id:Long=0,
    val name:String="",
    val lat:Double=0.0,
    val lon:Double=0.0,
    val address:String="",
    val timeStamp:String = System.currentTimeMillis().toString()

)
