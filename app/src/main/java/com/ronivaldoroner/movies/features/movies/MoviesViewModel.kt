package com.ronivaldoroner.movies.features.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ronivaldoroner.movies.base.BaseViewModel
import com.ronivaldoroner.movies.business.MovieBusiness
import com.ronivaldoroner.movies.commons.UIState
import com.ronivaldoroner.movies.domain.models.BaseModel
import com.ronivaldoroner.movies.domain.models.MovieModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
class MoviesViewModel(
    private val business: MovieBusiness
) : BaseViewModel() {

    private val _screen = MutableStateFlow<UIState<BaseModel>>(UIState.Loading)
    val screen: StateFlow<UIState<BaseModel>> get() = _screen

    private val _movieModel = MutableStateFlow<MovieModel?>(null)
    val movieModel: StateFlow<MovieModel?> get() = _movieModel

    private val _filters = MutableLiveData<Map<String, String>>()
    val filters: LiveData<Map<String, String>> get() = _filters

    init {
        screen
            .onStart { getMovie() }
            .onEach { handleState(it) }
            .launchIn(viewModelScope)
    }

    fun getMovie() {
        launchModel(data = _screen, function = { business.getMovie(filters.value) })
    }

    override fun <T> handleSuccess(data: T) {
        when (data) {
            is MovieModel -> _movieModel.value = data
        }
    }

    override fun handleError(code: Int?, message: String?) {
        _screen.value = UIState.Failure(code, message)
    }
}