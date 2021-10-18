package com.hsmnzaydn.satellites.features.satellite.data.entities


import com.google.gson.annotations.SerializedName

data class Position(
    @SerializedName("posX")
    var posX: Double,
    @SerializedName("posY")
    var posY: Double
)