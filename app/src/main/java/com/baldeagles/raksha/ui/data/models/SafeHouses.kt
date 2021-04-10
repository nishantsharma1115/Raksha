package com.baldeagles.raksha.ui.data.models

data class SafeHouses(
    val name:String="",
    val lat:Double=0.0,
    val lon:Double=0.0,
    val address:String="",
    val timeStamp:String = System.currentTimeMillis().toString()

)
