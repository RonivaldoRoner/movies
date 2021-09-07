package com.ronivaldoroner.movies.providers.remote.di

import com.ronivaldoroner.movies.domain.providers.MovieProvider
import com.ronivaldoroner.movies.providers.remote.Retrofit
import com.ronivaldoroner.movies.providers.remote.apis.MovieApi
import com.ronivaldoroner.movies.providers.remote.services.MovieRemoteProvider
import org.koin.dsl.module

val remoteModule = module {
    single { Retrofit.createWebService(MovieApi::class.java) }

    factory<MovieProvider> {
        MovieRemoteProvider(
            api = get()
        )
    }
}

