package com.guilhermekunz.cfapp

import android.app.Application
import com.guilhermekunz.cfapp.di.repositoryModule
import com.guilhermekunz.cfapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@Application)
            koin.loadModules(
                listOf(
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}