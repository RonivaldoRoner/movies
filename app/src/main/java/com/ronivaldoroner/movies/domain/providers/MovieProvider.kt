package com.ronivaldoroner.movies.domain.providers

import com.ronivaldoroner.movies.domain.models.MovieModel
import com.ronivaldoroner.movies.providers.remote.commons.ResultRemote

interface MovieProvider {
    suspend fun getMovie(params: Map<String, String>?): ResultRemote<MovieModel>
}