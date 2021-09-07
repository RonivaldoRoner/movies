package com.ronivaldoroner.movies.providers.remote.services

import com.ronivaldoroner.movies.domain.models.MovieModel
import com.ronivaldoroner.movies.domain.providers.MovieProvider
import com.ronivaldoroner.movies.providers.remote.apis.MovieApi
import com.ronivaldoroner.movies.providers.remote.commons.ResultRemote
import com.ronivaldoroner.movies.providers.remote.commons.mapRemoteErrors

class MovieRemoteProvider(
    private val api: MovieApi
) : MovieProvider {

    override suspend fun getMovie(params: Map<String, String>?): ResultRemote<MovieModel> = try {
        val result = api.getMovie(params)
        when {
            result.isSuccessful && result.body() != null -> ResultRemote.Success(result.body()!!)
            else -> ResultRemote.ErrorResponse.Generic(
                message = result.message(),
                codError = result.code()
            )
        }
    } catch (ex: Exception) {
        ex.mapRemoteErrors()
    }

}