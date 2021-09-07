package com.ronivaldoroner.movies.providers.remote.commons

import retrofit2.HttpException
import java.io.IOException

fun Throwable.mapRemoteErrors(): ResultRemote.ErrorResponse.Generic {
    return when (this) {
        is HttpException -> {
            when (code()) {
                else -> ResultRemote.ErrorResponse.Generic(message(), code())
            }
        }
        is IOException -> {
            ResultRemote.ErrorResponse.Generic(message, -1)
        }
        else -> ResultRemote.ErrorResponse.Generic(message, -1)
    }
}