package com.hsmnzaydn.satellites.ui.main

import android.content.Intent
import android.util.Log
import androidx.hilt.Assisted
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.hsmnzaydn.satellites.base.BaseViewModel
import com.hsmnzaydn.satellites.base.collectUI
import com.hsmnzaydn.satellites.features.satellite.domain.usecase.SatelliteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainVM @Inject constructor(
    @Assisted private val stateHandle: SavedStateHandle) : BaseViewModel() {

}