package com.werockstar.reactiveandroid.di


import android.app.Application

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
class AndroidModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplication() = application
}
