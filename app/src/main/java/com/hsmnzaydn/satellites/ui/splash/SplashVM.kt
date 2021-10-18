package com.hsmnzaydn.satellites.ui.splash

import androidx.hilt.Assisted
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import com.hsmnzaydn.satellites.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SplashVM @Inject constructor(@Assisted private val stateHandle: SavedStateHandle) :
    BaseViewModel() {

}