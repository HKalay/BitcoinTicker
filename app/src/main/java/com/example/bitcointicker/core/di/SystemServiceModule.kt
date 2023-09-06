package com.example.bitcointicker.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.example.bitcointicker.core.utils.pref.SharedPrefManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SystemServiceModule {

    @Provides
    @Singleton
    fun provideSharedPrefManager(@ApplicationContext context: Context) =
        SharedPrefManager(context)
}