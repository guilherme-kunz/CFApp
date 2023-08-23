package com.guilhermekunz.cfapp.di


import com.guilhermekunz.cfapp.api.client.ClientRetrofit
import com.guilhermekunz.cfapp.utils.constants.Constants.BASE_URL
import org.koin.dsl.module

val dataModule = module {
    single {
        ClientRetrofit.create(BASE_URL)
    }
}