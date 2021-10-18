package com.hsmnzaydn.satellites.utils.fragment_controller.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize




@Parcelize
data class Controller(
    var controllerName: String,
    var containerId: Int
) : Parcelable