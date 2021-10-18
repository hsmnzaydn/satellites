package com.hsmnzaydn.satellites.ui.satellite_detail

import androidx.hilt.Assisted
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.hsmnzaydn.satellites.base.BaseViewModel
import com.hsmnzaydn.satellites.base.CoreDataState
import com.hsmnzaydn.satellites.base.SingleMutableData
import com.hsmnzaydn.satellites.base.collectUI
import com.hsmnzaydn.satellites.features.satellite.data.entities.Position
import com.hsmnzaydn.satellites.features.satellite.domain.entities.Satellite
import com.hsmnzaydn.satellites.features.satellite.domain.usecase.SatelliteUseCase
import com.hsmnzaydn.satellites.utils.event
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SatelliteDetailVM @Inject constructor(@Assisted private val stateHandle: SavedStateHandle,
                                            private val satelliteUseCase: SatelliteUseCase
) :
    BaseViewModel() {

    val satelliteDetail = SingleMutableData<CoreDataState<Satellite>>()
    val satellitePosition = SingleMutableData<CoreDataState<Position>>()

    fun fetchSatelliteDetail(id:Int) {
        viewModelScope.launch {
            satelliteUseCase.getSatelliteDetail(id).collectUI(this@SatelliteDetailVM) {
                satelliteDetail.value = it.event()
            }
        }
    }

    fun fetchSatellitePosition(id: Int){
        viewModelScope.launch {
            satelliteUseCase.getSatellitePosition(id).collect {
                satellitePosition.value = it.event()
            }
        }
    }

}