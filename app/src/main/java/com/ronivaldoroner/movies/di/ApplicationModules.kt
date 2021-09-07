package com.ronivaldoroner.movies.di

import com.ronivaldoroner.movies.features.movies.moviesModule
import com.ronivaldoroner.movies.providers.remote.di.remoteModule
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
val applicationModules = listOf(
    moviesModule,
    remoteModule
)