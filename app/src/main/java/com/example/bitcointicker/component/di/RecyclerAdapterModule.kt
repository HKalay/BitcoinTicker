package com.example.bitcointicker.component.di

import com.example.bitcointicker.component.recyclerview.RecyclerviewAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RecyclerAdapterModule {

    @Provides
    fun provideAdapter(): RecyclerviewAdapter {
        return RecyclerviewAdapter()
    }
}