package com.hsmnzaydn.satellites.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hsmnzaydn.satellites.utils.Event


typealias SingleLiveData<T> = LiveData<Event<T>>
typealias SingleMutableData<T> = MutableLiveData<Event<T>>
typealias MutableDataState<T> = MutableLiveData<CoreDataState<T>>