package com.guilhermekunz.cfapp.api.request

import retrofit2.Response
import retrofit2.http.GET

interface Requests {

    @GET("facts")
    suspend fun searchZipCode(): Response<>

}