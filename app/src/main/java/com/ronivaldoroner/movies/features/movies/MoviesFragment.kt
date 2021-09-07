package com.ronivaldoroner.movies.features.movies

import com.ronivaldoroner.movies.R
import com.ronivaldoroner.movies.base.BaseFragment
import com.ronivaldoroner.movies.databinding.MovieFragmentBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : BaseFragment<MovieFragmentBinding>() {
    override val layoutId = R.layout.movie_fragment

    @ExperimentalCoroutinesApi
    private val viewModel: MoviesViewModel by viewModel()

    override fun initControllers(binding: MovieFragmentBinding) {
        super.initControllers(binding)
        binding.viewModel = viewModel
    }

    override fun initListeners(binding: MovieFragmentBinding) {
        super.initListeners(binding)
    }

    override fun initObservables(binding: MovieFragmentBinding) {
        super.initObservables(binding)

    }
}