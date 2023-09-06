package com.example.bitcointicker.component.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.example.bitcointicker.component.ui.RecyclerviewAdapter

@Module
@InstallIn(SingletonComponent::class)
class RecyclerAdapterModule {

    @Provides
    fun provideAdapter(): RecyclerviewAdapter {
        return RecyclerviewAdapter()
    }
}