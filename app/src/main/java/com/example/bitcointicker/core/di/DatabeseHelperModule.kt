package com.example.bitcointicker.core.di

import android.content.Context
import com.example.bitcointicker.core.helpers.DatabeseHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.example.bitcointicker.core.utils.pref.SharedPrefManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabeseHelperModule {

    @Provides
    @Singleton
    fun provideDatabeseHelper() =
        DatabeseHelper()
}