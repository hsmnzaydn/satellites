package com.hsmnzaydn.satellites.utils

import androidx.lifecycle.MutableLiveData


fun <T> MutableLiveData<List<T>>.add(item: T) {
    val temp = this.value?.toMutableList()
    temp?.add(item)
    this.value = temp ?: emptyList()
}

fun <T> MutableLiveData<List<T>>.addAll(item: List<T>) {
    val temp = this.value?.toMutableList()
    temp?.addAll(item)
    this.value = temp ?: emptyList()
}

fun <T> MutableLiveData<List<T>>.removeAt(index: Int) {
    val temp = this.value?.toMutableList()
    temp?.removeAt(index)
    this.value = temp ?: emptyList()
}