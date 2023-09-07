package com.example.bitcointicker.core.di

import com.example.bitcointicker.core.helpers.DatabeseHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabeseHelperModule {

    @Provides
    @Singleton
    fun provideDatabeseHelper() =
        DatabeseHelper()
}