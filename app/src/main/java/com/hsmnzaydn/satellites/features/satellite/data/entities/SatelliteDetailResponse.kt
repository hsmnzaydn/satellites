package com.hsmnzaydn.satellites.features.satellite.data.entities


import com.google.gson.annotations.SerializedName

data class SatelliteDetailResponse(
    @SerializedName("cost_per_launch")
    var costPerLaunch: Double,
    @SerializedName("first_flight")
    var firstFlight: String,
    @SerializedName("height")
    var height: Int,
    @SerializedName("id")
    var id: Int,
    @SerializedName("mass")
    var mass: Int
)