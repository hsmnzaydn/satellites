package com.hsmnzaydn.satellites.utils.fragment_controller.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize



@Parcelize
data class FragmentStack(
    var tag: String,
    var controllerName: String,
    var tabMenuFragment: Boolean = false,
    var groupId: Int = 0,
    var firstTabFragment: Boolean = false,
    var popupFragmentTag: String = ""
) : Parcelable
