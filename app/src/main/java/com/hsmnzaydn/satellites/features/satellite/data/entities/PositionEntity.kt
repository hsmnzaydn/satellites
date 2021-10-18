package com.hsmnzaydn.satellites.features.satellite.data.entities


import com.google.gson.annotations.SerializedName

data class PositionEntity(
    @SerializedName("id")
    var id: String,
    @SerializedName("positions")
    var positions: List<Position>
)