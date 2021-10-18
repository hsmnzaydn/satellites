package com.hsmnzaydn.satellites.features.satellite.data.entities


import com.google.gson.annotations.SerializedName

data class PositionResponse(
    @SerializedName("list")
    var list: List<PositionEntity>
)