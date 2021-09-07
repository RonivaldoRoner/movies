package com.ronivaldoroner.movies.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    /**
     * The layout id for the [ViewDataBinding] for [T]
     */
    abstract val layoutId: Int

    /**
     * This reference must not be used before [onViewCreated] or a [NullPointerException]
     * will be throw
     */
    protected var binding: T? = null

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding?.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.lifecycleOwner = viewLifecycleOwner
        initControllers(binding!!)
        initListeners(binding!!)
        initObservables(binding!!)
    }

    /**
     * @param binding a binding for [layoutId] already tied to the fragment's lifecycle
     */
//    private fun onDataBound(binding: T) {
//        initControllers(binding)
//        initListeners(binding)
//    }

    open fun initControllers(binding: T) {

    }

    open fun initListeners(binding: T) {

    }

    open fun initObservables(binding: T) {

    }
}