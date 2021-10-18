package com.hsmnzaydn.satellites.base

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hsmnzaydn.satellites.utils.event

open class BaseViewModel : ViewModel() {


    val timerRestart = MutableLiveData<Boolean>()

    val loading = SingleMutableData<Boolean>()

    val errorHandle = SingleMutableData<String>()

    fun showLoading() {
        loading.value = true.event()
    }

    fun hideLoading() {
        loading.value = false.event()
    }

    fun delay(duration: Long, run: () -> Unit) {
        Handler().postDelayed({
            run()
        }, duration)
    }
}