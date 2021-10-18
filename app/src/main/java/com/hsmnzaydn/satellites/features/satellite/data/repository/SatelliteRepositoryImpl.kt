package com.hsmnzaydn.satellites.features.satellite.data.repository

import android.content.Context
import com.google.gson.Gson
import com.hsmnzaydn.satellites.features.satellite.data.entities.Position
import com.hsmnzaydn.satellites.features.satellite.data.entities.PositionResponse
import com.hsmnzaydn.satellites.features.satellite.data.entities.SatelliteDetailResponse
import com.hsmnzaydn.satellites.features.satellite.data.entities.SatelliteResponse
import com.hsmnzaydn.satellites.features.satellite.domain.repository.SatelliteRepository
import com.hsmnzaydn.satellites.utils.CoreCommonUtils

class SatelliteRepositoryImpl(val context:Context,val gson: Gson):SatelliteRepository{
    var listSatelliteDetailResponse =  ArrayList<SatelliteDetailResponse>()

    override suspend fun getSatellites(): List<SatelliteResponse> {
        var data = CoreCommonUtils.loadJSONFromAsset(context,"satellites")
        var response = gson.fromJson(data, Array<SatelliteResponse>::class.java)
        return response.toList()
    }

    override suspend fun getSatelliteDetail(satellideId: Int): SatelliteDetailResponse? {
        listSatelliteDetailResponse.find {
            it.id == satellideId
        }.apply {
            this?.let {
                return it
            }?: kotlin.run {
                var data = CoreCommonUtils.loadJSONFromAsset(context,"satellite-detail")
                var response = gson.fromJson(data, Array<SatelliteDetailResponse>::class.java)

                response.find {
                    it.id.equals(satellideId)
                }.apply {
                    this?.let {
                        listSatelliteDetailResponse.add(it)
                    }
                    return this
                }
            }
        }
        return null
    }

    override suspend fun getSatellitePosition(satellideId: Int): Position? {
        var data = CoreCommonUtils.loadJSONFromAsset(context,"positions")
        var response = gson.fromJson(data, PositionResponse::class.java)
        response.list.find {
            it.id.toInt().equals(satellideId)
        }.apply {
           return this?.let {
               it.positions.get((0..2).random())
            }
        }
    }

    override suspend fun searchSatellites(query: String): List<SatelliteResponse> {
        var data = CoreCommonUtils.loadJSONFromAsset(context,"satellites")
        var response = gson.fromJson(data, Array<SatelliteResponse>::class.java)
        response.filter {
            it.name.contains(query)
        }.apply {
            return this
        }
    }

}