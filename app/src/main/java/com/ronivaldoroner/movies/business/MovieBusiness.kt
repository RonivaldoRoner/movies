package com.ronivaldoroner.movies.business

import com.ronivaldoroner.movies.domain.providers.MovieProvider

class MovieBusiness(
    private val provider: MovieProvider
) {

    suspend fun getMovie(params: Map<String, String>?) = provider.getMovie(params)

}