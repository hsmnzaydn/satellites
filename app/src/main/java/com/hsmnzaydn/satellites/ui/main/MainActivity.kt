package com.hsmnzaydn.satellites.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.hsmnzaydn.satellites.R
import com.hsmnzaydn.satellites.base.view.BaseActivity
import com.hsmnzaydn.satellites.databinding.ActivityMainBinding
import com.hsmnzaydn.satellites.ui.satellitelist.SatelliteListFragment
import com.hsmnzaydn.satellites.utils.fragment_controller.FragmentController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    val viewModel: MainVM by viewModels()

    private lateinit var binding: ActivityMainBinding
    val navigator = FragmentController(this, R.id.rootView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigator.apply {
            init(null)
            startFragment(SatelliteListFragment.newInstance()) {
            }
        }

        navigator.onBackPressDetect {
            //closeKeyboard()
        }

    }

    override fun onBackPressed() {
        navigator.onBackPressed()
    }
}