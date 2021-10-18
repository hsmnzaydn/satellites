package com.hsmnzaydn.satellites.ui.satellitelist

import androidx.hilt.Assisted
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.hsmnzaydn.satellites.base.BaseViewModel
import com.hsmnzaydn.satellites.base.CoreDataState
import com.hsmnzaydn.satellites.base.SingleMutableData
import com.hsmnzaydn.satellites.base.collectUI
import com.hsmnzaydn.satellites.features.satellite.domain.entities.SatelliteListItem
import com.hsmnzaydn.satellites.features.satellite.domain.usecase.SatelliteUseCase
import com.hsmnzaydn.satellites.utils.event
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SatelliteListVM @Inject constructor(
    @Assisted private val stateHandle: SavedStateHandle,
    private val satelliteUseCase: SatelliteUseCase
) :
    BaseViewModel() {
    val satelliteList = SingleMutableData<CoreDataState<List<SatelliteListItem>>>()

    init {
        fetchSatellites()
    }

    private fun fetchSatellites() {
        viewModelScope.launch {
            satelliteUseCase.fetchSatellites().collectUI(this@SatelliteListVM) {
                satelliteList.value = it.event()
            }
        }
    }

    fun searchSatellites(query: String) {
        if (query.isEmpty()) {
            fetchSatellites()
        } else {
            viewModelScope.launch {
                satelliteUseCase.searchSatellites(query).collectUI(this@SatelliteListVM) {
                    satelliteList.value = it.event()
                }
            }
        }
    }

}