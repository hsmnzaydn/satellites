package com.hsmnzaydn.satellites.ui.adapters

import android.view.ViewGroup
import com.hsmnzaydn.satellites.R
import com.hsmnzaydn.satellites.base.BaseAdapter
import com.hsmnzaydn.satellites.base.BaseViewHolder
import com.hsmnzaydn.satellites.base.model.BaseEntity
import com.hsmnzaydn.satellites.databinding.RowSatelliteBinding
import com.hsmnzaydn.satellites.features.satellite.domain.entities.SatelliteListItem

class SatelliteListAdapter() : BaseAdapter<BaseEntity>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BaseEntity> {
        return SatelliteViewHolder(
            parent
        ) as BaseViewHolder<BaseEntity>
    }

    class SatelliteViewHolder(
        parent: ViewGroup
    ) : BaseViewHolder<SatelliteListItem>(
        parent,
        LAYOUT_ID
    ) {
        val binding = RowSatelliteBinding.bind(itemView)

        override fun bindItem(item: SatelliteListItem) {
            with(binding) {
                satelliteNameTextView.setText(item.name)
                if(item.status){
                    satelliteStatusTextView.setText(context.getString(R.string.common_satellite_active))
                    satelliteStatusTextView.setTextColor(context.getColor(R.color.black))
                    satelliteNameTextView.setTextColor(context.getColor(R.color.black))

                }else{
                    satelliteStatusTextView.setText(context.getString(R.string.common_satellite_disable))
                    satelliteStatusTextView.setTextColor(context.getColor(R.color.grey))
                    satelliteNameTextView.setTextColor(context.getColor(R.color.grey))
                    cardview.cardElevation = 0f
                }


            }
        }

        companion object {
            const val LAYOUT_ID = R.layout.row_satellite
        }

    }

}