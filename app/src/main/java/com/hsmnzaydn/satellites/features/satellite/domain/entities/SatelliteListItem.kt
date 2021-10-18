package com.hsmnzaydn.satellites.features.satellite.domain.entities

import android.os.Parcelable
import com.hsmnzaydn.satellites.base.model.BaseEntity
import com.hsmnzaydn.satellites.ui.adapters.SatelliteListAdapter

data class SatelliteListItem(
    var id: Int,
    var name: String,
    var status: Boolean
): BaseEntity(SatelliteListAdapter.SatelliteViewHolder.LAYOUT_ID)
