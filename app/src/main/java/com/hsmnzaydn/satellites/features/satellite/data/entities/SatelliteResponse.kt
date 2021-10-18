package com.hsmnzaydn.satellites.features.satellite.data.entities


import com.google.gson.annotations.SerializedName

data class SatelliteResponse(
    @SerializedName("active")
    var active: Boolean,
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String
)