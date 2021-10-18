package com.hsmnzaydn.satellites.base.view

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.viewbinding.ViewBinding
import com.hsmnzaydn.satellites.utils.CoreEventObserver
import com.hsmnzaydn.satellites.base.BaseViewModel
import com.hsmnzaydn.satellites.base.SharedViewModel
import com.hsmnzaydn.satellites.ui.main.MainActivity
import com.hsmnzaydn.satellites.utils.fragment_controller.FragmentController
import com.hsmnzaydn.satellites.utils.fragment_controller.FragmentOption


abstract class BaseFragment<ViewModel : BaseViewModel, T : ViewBinding> : Fragment() {

    private var baseActivity: BaseActivity? = null

    private lateinit var mainActivity: MainActivity
    lateinit var navigator: FragmentController

    private var _binding: T? = null

    val safeBinding get() = _binding
    val binding get() = _binding!!


    abstract fun getViewBinding(): T

    protected val viewModel: ViewModel by lazy { ViewModelProvider(this).get(getViewModelClass()) }
    protected abstract fun getViewModelClass(): Class<ViewModel>

    private var runOnce = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding()

        initUI(inflater, container, savedInstanceState)

        if (!runOnce) {
            runOnce = true
            runOnce()
        } else {
            againOpened()
        }


        setOnClickListener()
        subscribeObservers()


        return binding.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            this.baseActivity = context
        }

        if (context is MainActivity) {
            mainActivity = context
            navigator = context.navigator
        }
    }


    fun mainNavigate(
        fragment: Fragment,
        block: FragmentOption.Builder.() -> Unit = {}
    ) {
        navigator.mainNavigate(FragmentOption.build(fragment, block))


    }

    fun childNavigate(
        fragment: Fragment,
        block: FragmentOption.Builder.() -> Unit = {}
    ) {
        navigator.childNavigate(FragmentOption.build(fragment, block))
    }

    fun navigateUp() {
        navigator.navigateUp()
    }

    open fun runOnce() {}

    open fun initUI(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {
    }

    open fun againOpened() {}

    open fun subscribeObservers() {
        viewModel.loading.observe(viewLifecycleOwner, loading)
        viewModel.errorHandle.observe(viewLifecycleOwner, errorHandle)
    }

    open fun setOnClickListener() {}

    private val errorHandle = CoreEventObserver<String> {
        Toast.makeText(requireActivity(),it,Toast.LENGTH_LONG).show()
    }

    private val loading = CoreEventObserver<Boolean> {
        if (it) {
            showLoading()
        } else {
            hideLoading()
        }
    }

    open fun delay(duration: Long, run: () -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed({
            run()
        }, duration)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.loading.removeObserver(loading)
        viewModel.errorHandle.removeObserver(errorHandle)
        _binding = null
    }

    fun sharedViewModel(): SharedViewModel =
        ViewModelProviders.of(requireActivity()).get(SharedViewModel::class.java)

    open fun showLoading() {
        baseActivity?.showLoading()
    }

    open fun hideLoading() {
        baseActivity?.hideLoading()
    }


    open fun showInformation(text: String) {
        if (baseActivity != null) {
            baseActivity?.showError(text)
        }
    }

    fun getMainActivity(): MainActivity? {
        return if (this::mainActivity.isInitialized) {
            mainActivity
        } else {
            null
        }
    }

    fun finish() {
        activity?.finish()
    }


    open fun showError(message: String) {
        if (baseActivity != null) {
            baseActivity?.showError(message)
        }
    }


}
