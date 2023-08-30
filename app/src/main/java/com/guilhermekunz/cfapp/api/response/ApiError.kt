package com.guilhermekunz.cfapp.api.response

object ApiError {

    data class GenericException(override val message: String? = null) : Exception()

}