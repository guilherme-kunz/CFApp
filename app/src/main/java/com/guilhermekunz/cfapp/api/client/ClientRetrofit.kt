package com.guilhermekunz.cfapp.api.client

import com.guilhermekunz.cfapp.api.request.Requests
import com.guilhermekunz.cfapp.utils.constants.Constants.TIME_OUT
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ClientRetrofit {

    companion object {

        fun create(url:String): Requests {
            val logger = HttpLoggingInterceptor()
            val client = OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(logger)
                .build()
            return  Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(Requests::class.java)
        }

    }

}