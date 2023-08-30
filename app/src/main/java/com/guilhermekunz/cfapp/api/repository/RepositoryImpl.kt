package com.guilhermekunz.cfapp.api.repository

import com.guilhermekunz.cfapp.api.request.Requests
import com.guilhermekunz.cfapp.api.response.ApiError
import com.guilhermekunz.cfapp.api.response.FactsResponse
import com.guilhermekunz.cfapp.api.response.NetworkResponse

class RepositoryImpl(private val api: Requests) : Repository {

    override suspend fun getCatsFacts(): NetworkResponse<FactsResponse> {
        return try {
            val response = api.getCatsFacts()
            if (response.isSuccessful) {
                NetworkResponse.Success(response.body()!!)
            } else {
                NetworkResponse.Failed(Exception())
            }
        } catch (e: java.lang.Exception) {
            NetworkResponse.Failed(ApiError.GenericException())
        }
    }

}