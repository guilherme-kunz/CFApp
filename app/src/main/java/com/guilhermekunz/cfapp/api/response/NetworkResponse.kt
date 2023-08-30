package com.guilhermekunz.cfapp.api.response

import java.lang.Exception

sealed class NetworkResponse<out T> {

    data class Success<T>(val data: T) : NetworkResponse<T>()

    data class Failed(val error: Exception) : NetworkResponse<Nothing>()

}
