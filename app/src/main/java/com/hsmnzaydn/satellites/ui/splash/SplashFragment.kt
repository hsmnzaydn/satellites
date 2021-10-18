package com.hsmnzaydn.satellites.ui.splash

import com.hsmnzaydn.satellites.base.view.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.hsmnzaydn.satellites.databinding.FragmentSplashBinding
import com.hsmnzaydn.satellites.ui.satellite_detail.SatelliteDetailFragment
import com.hsmnzaydn.satellites.ui.satellitelist.SatelliteListFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashVM, FragmentSplashBinding>() {
    override fun getViewModelClass() = SplashVM::class.java
    override fun getViewBinding() = FragmentSplashBinding.inflate(layoutInflater)
    override fun initUI(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {
        lifecycleScope.launch {
            kotlinx.coroutines.delay(1500)
            mainNavigate(SatelliteListFragment.newInstance())
        }
    }

    companion object {
        fun newInstance() = SplashFragment()
    }
}
