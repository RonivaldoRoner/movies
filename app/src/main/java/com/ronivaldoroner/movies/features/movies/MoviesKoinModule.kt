package com.ronivaldoroner.movies.features.movies

import com.ronivaldoroner.movies.business.MovieBusiness
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val moviesModule = module {
    factory {
        MovieBusiness(
            provider = get()
        )
    }

    viewModel {
        MoviesViewModel(
            business = get()
        )
    }
}