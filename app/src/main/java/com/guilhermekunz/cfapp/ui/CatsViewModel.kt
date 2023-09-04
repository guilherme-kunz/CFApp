package com.guilhermekunz.cfapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guilhermekunz.cfapp.api.repository.Repository
import com.guilhermekunz.cfapp.api.response.FactsResponse
import com.guilhermekunz.cfapp.api.response.NetworkResponse
import kotlinx.coroutines.launch

class CatsViewModel(val repository: Repository) : ViewModel() {

    var loadingStateLiveData = MutableLiveData<State>()

    private val _catsFactsResponse = MutableLiveData<FactsResponse?>()
    val catsFactsResponse: LiveData<FactsResponse?> =
        _catsFactsResponse

    private val _error = MutableLiveData<Unit>()
    val error = _error as LiveData<Unit>

    fun getCatsFacts() {
        viewModelScope.launch {
            loadingStateLiveData.value = State.LOADING
            when (val response = repository.getCatsFacts()) {
                is NetworkResponse.Success -> {
                    _catsFactsResponse.value = response.data
                }
                is NetworkResponse.Failed -> {
                    _error.value = Unit
                }
            }
            loadingStateLiveData.value = State.LOADING_FINISH
        }
    }

    enum class State {
        LOADING, LOADING_FINISH
    }

}