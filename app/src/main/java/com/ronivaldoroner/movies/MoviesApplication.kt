package com.ronivaldoroner.movies

import android.app.Application
import com.ronivaldoroner.movies.di.applicationModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class MoviesApplication : Application() {

    @ExperimentalCoroutinesApi
    override fun onCreate() {
        super.onCreate()
        buildDependencyGraph()

    }

    @ExperimentalCoroutinesApi
    private fun buildDependencyGraph() {
        startKoin {
            androidContext(this@MoviesApplication)
            modules(applicationModules)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}