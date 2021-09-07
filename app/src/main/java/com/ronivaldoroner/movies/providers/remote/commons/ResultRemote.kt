package com.ronivaldoroner.movies.providers.remote.commons

sealed class ResultRemote<out T> {
    data class Success<out T>(val response: T) : ResultRemote<T>()

    sealed class ErrorResponse(open val message: String?, open val codError: Int?) :
        ResultRemote<Nothing>() {
        data class Generic(override val message: String?, override val codError: Int?) :
            ErrorResponse(message, codError)
    }
}