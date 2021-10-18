package com.hsmnzaydn.satellites.features.satellite.domain.usecase

import com.hsmnzaydn.satellites.base.CoreDataState
import com.hsmnzaydn.satellites.features.satellite.data.entities.Position
import com.hsmnzaydn.satellites.features.satellite.domain.entities.Satellite
import com.hsmnzaydn.satellites.features.satellite.domain.entities.SatelliteListItem
import com.hsmnzaydn.satellites.features.satellite.domain.repository.SatelliteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import toSatellite
import toSatelliteLisItem
import javax.inject.Inject

class SatelliteUseCase @Inject constructor(private val satelliteRepository: SatelliteRepository){

    suspend fun fetchSatellites(): Flow<CoreDataState<List<SatelliteListItem>>> {
        return flow {
            emit(CoreDataState.loading())
            var result = satelliteRepository.getSatellites()
            result?.let {
                emit(CoreDataState.success(result.map {
                    it.toSatelliteLisItem()
                }))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSatelliteDetail(satellideId:Int): Flow<CoreDataState<Satellite>> {
        return flow {
            emit(CoreDataState.loading())
            var result = satelliteRepository.getSatelliteDetail(satellideId)
            result?.let {
                emit(CoreDataState.success(result.toSatellite()))
            }?: kotlin.run {
                emit(CoreDataState.error<Satellite>())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSatellitePosition(satellideId:Int): Flow<CoreDataState<Position>> {
        return flow {
            emit(CoreDataState.loading())
            var result = satelliteRepository.getSatellitePosition(satellideId)
            result?.let {
                emit(CoreDataState.success(result))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun searchSatellites(query:String): Flow<CoreDataState<List<SatelliteListItem>>> {
        return flow {
            emit(CoreDataState.loading())
            var result = satelliteRepository.searchSatellites(query)
            result?.let {
                emit(CoreDataState.success(result.map {
                    it.toSatelliteLisItem()
                }))
            }
        }.flowOn(Dispatchers.IO)
    }
}