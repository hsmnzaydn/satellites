package com.hsmnzaydn.satellites.features.satellite.domain.repository

import com.hsmnzaydn.satellites.features.satellite.data.entities.Position
import com.hsmnzaydn.satellites.features.satellite.data.entities.SatelliteDetailResponse
import com.hsmnzaydn.satellites.features.satellite.data.entities.SatelliteResponse

interface SatelliteRepository{
    suspend fun getSatellites(): List<SatelliteResponse>
    suspend fun getSatelliteDetail(satellideId:Int): SatelliteDetailResponse?
    suspend fun getSatellitePosition(satellideId:Int): Position?
    suspend fun searchSatellites(query:String):List<SatelliteResponse>
}