package com.hsmnzaydn.satellites.utils.fragment_controller.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Kemal Tunç on 2020-09-30
 */


@Parcelize
data class Controller(
    var controllerName: String,
    var containerId: Int
) : Parcelable