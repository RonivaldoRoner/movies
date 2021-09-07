package com.ronivaldoroner.movies.providers.remote.apis

import com.ronivaldoroner.movies.domain.models.MovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MovieApi {
    @GET
    suspend fun getMovie(
        @QueryMap(encoded = true) options: Map<String, String>?
    ): Response<MovieModel>
}