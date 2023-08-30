package com.guilhermekunz.cfapp.api.repository

import com.guilhermekunz.cfapp.api.response.FactsResponse
import com.guilhermekunz.cfapp.api.response.NetworkResponse

interface Repository {

    suspend fun getCatsFacts(): NetworkResponse<FactsResponse>

}