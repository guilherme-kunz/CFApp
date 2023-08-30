package com.guilhermekunz.cfapp.api.request

import com.guilhermekunz.cfapp.api.response.FactsResponse
import retrofit2.Response
import retrofit2.http.GET

interface Requests {

    @GET("facts")
    suspend fun getCatsFacts(): Response<FactsResponse>

}