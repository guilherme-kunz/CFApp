package com.guilhermekunz.cfapp.di


import com.guilhermekunz.cfapp.api.client.ClientRetrofit
import com.guilhermekunz.cfapp.api.repository.Repository
import com.guilhermekunz.cfapp.api.repository.RepositoryImpl
import com.guilhermekunz.cfapp.ui.CatsViewModel
import com.guilhermekunz.cfapp.utils.constants.Constants.BASE_URL
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single<Repository> {
        RepositoryImpl(
            api = ClientRetrofit.create(BASE_URL)
        )
    }
}

val viewModelModule = module {
    viewModel { CatsViewModel(repository = get()) }
}