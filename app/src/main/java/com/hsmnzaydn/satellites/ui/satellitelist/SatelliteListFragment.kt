package com.hsmnzaydn.satellites.ui.satellitelist

import com.hsmnzaydn.satellites.base.view.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hsmnzaydn.satellites.base.CoreDataState
import com.hsmnzaydn.satellites.databinding.FragmentSatelliteListBinding
import com.hsmnzaydn.satellites.features.satellite.domain.entities.SatelliteListItem
import com.hsmnzaydn.satellites.ui.adapters.SatelliteListAdapter
import com.hsmnzaydn.satellites.ui.satellite_detail.SatelliteDetailFragment
import com.hsmnzaydn.satellites.utils.CoreEventObserver
import com.hsmnzaydn.satellites.utils.gone
import com.hsmnzaydn.satellites.utils.show
import com.hsmnzaydn.satellites.utils.vertical

@AndroidEntryPoint
class SatelliteListFragment : BaseFragment<SatelliteListVM, FragmentSatelliteListBinding>() {
    override fun getViewModelClass() = SatelliteListVM::class.java
    override fun getViewBinding() = FragmentSatelliteListBinding.inflate(layoutInflater)

    private val adapter by lazy { SatelliteListAdapter() }

    override fun initUI(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {
        initAdapter()
        initToolbar()
    }

    private fun initToolbar() {
        binding.toolbar.getSearchInput {
            viewModel.searchSatellites(it)
        }
    }

    private fun initAdapter() {
        adapter.vertical(binding.recylerview).onItemClick { baseEntity, positionItem, layoutId ->
            when(baseEntity){
                is SatelliteListItem ->{
                    var entity = baseEntity as SatelliteListItem
                    if(entity.status){
                        mainNavigate(SatelliteDetailFragment.newInstance(entity.id,entity.name))
                    }
                }
            }
        }
    }

    override fun subscribeObservers() {
        super.subscribeObservers()
        viewModel.satelliteList.observe(viewLifecycleOwner, transactionList)
    }

    private val transactionList = CoreEventObserver<CoreDataState<List<SatelliteListItem>>> {
         it.data?.let {
             if(it.size == 0){
                 binding.emptyTextView.show()
                 binding.recylerview.gone()
             }else{
                 binding.emptyTextView.gone()
                 binding.recylerview.show()
                 adapter.items = it
             }
        }
    }

    companion object {
        fun newInstance() = SatelliteListFragment()
    }
}
