package com.ronivaldoroner.movies.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ronivaldoroner.movies.commons.UIState
import com.ronivaldoroner.movies.domain.models.BaseModel
import com.ronivaldoroner.movies.providers.remote.commons.ResultRemote
import com.ronivaldoroner.movies.providers.remote.commons.mapRemoteErrors
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
abstract class BaseViewModel : ViewModel() {

    fun launchModel(
        data: MutableStateFlow<UIState<BaseModel>>,
        function: suspend () -> ResultRemote<BaseModel>
    ) {
        data.value = UIState.Loading
        viewModelScope.launch {
            try {
                when (val result = function.invoke()) {
                    is ResultRemote.Success -> {
                        data.value = UIState.Success(result.response)
                    }
                    is ResultRemote.ErrorResponse -> {
                        data.value = UIState.Failure(result.codError, result.message)
                    }
                }
            } catch (ex: Throwable) {
                data.value =
                    UIState.Failure(ex.mapRemoteErrors().codError, ex.mapRemoteErrors().message)
            }
        }

    }

    fun <T> handleState(data: UIState<T>) {
        when (data) {
            is UIState.Success -> handleSuccess(data.result)
            is UIState.Failure -> handleError(data.code, data.message)
            else -> {
            }
        }
    }

    abstract fun <T> handleSuccess(data: T)

    abstract fun handleError(code: Int?, message: String?)
}