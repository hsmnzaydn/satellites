package com.hsmnzaydn.satellites.ui.satellite_detail

import com.hsmnzaydn.satellites.base.view.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.hsmnzaydn.satellites.base.CoreDataState
import com.hsmnzaydn.satellites.databinding.FragmentSatelliteDetailBinding
import com.hsmnzaydn.satellites.features.satellite.data.entities.Position
import com.hsmnzaydn.satellites.features.satellite.domain.entities.Satellite
import com.hsmnzaydn.satellites.utils.BundleConstant
import com.hsmnzaydn.satellites.utils.CoreEventObserver
import com.hsmnzaydn.satellites.utils.show
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SatelliteDetailFragment : BaseFragment<SatelliteDetailVM, FragmentSatelliteDetailBinding>() {
    override fun getViewModelClass() = SatelliteDetailVM::class.java
    override fun getViewBinding() = FragmentSatelliteDetailBinding.inflate(layoutInflater)
    var satelliteId = -1
    override fun initUI(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {
        arguments?.apply {
            satelliteId = getInt(BundleConstant.SATELLITE_ID, -1)
            viewModel.fetchSatelliteDetail(satelliteId)
            viewModel.fetchSatellitePosition(satelliteId)
            binding.toolbar.setTitle(getString(BundleConstant.SATELLITE_NAME, ""))
        }
    }

    override fun subscribeObservers() {
        super.subscribeObservers()
        viewModel.satelliteDetail.observe(viewLifecycleOwner, satelliteDetail)
        viewModel.satellitePosition.observe(viewLifecycleOwner, satellitePosition)
    }

    private val satelliteDetail = CoreEventObserver<CoreDataState<Satellite>> {
        it.data.apply {
            this?.let {
                binding.dateTextView.text = it.firstFlight
                binding.costTextView.text = it.costPerLaunch
                binding.heightmassTextView.text = "${it.height}/${it.mass}"

            }
        }
    }

    private val satellitePosition = CoreEventObserver<CoreDataState<Position>> {
        it.data.apply {
            this?.let {
                binding.lastPositionTextView.text = "${it.posX.toString()},${it.posY.toString()}"
                lifecycleScope.launch {
                    kotlinx.coroutines.delay(3000)
                    viewModel.fetchSatellitePosition(satelliteId)
                }
            }
        }
    }

    companion object {
        fun newInstance(satelliteId: Int, satelliteName: String) = SatelliteDetailFragment().apply {
            arguments = Bundle().apply {
                putInt(BundleConstant.SATELLITE_ID, satelliteId)
                putString(BundleConstant.SATELLITE_NAME, satelliteName)
            }
        }
    }
}
