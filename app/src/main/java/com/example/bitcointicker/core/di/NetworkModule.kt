package com.example.bitcointicker.core.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.bitcointicker.core.Constant
import com.example.bitcointicker.core.Constant.MAX_MEMORY_CACHE
import com.example.bitcointicker.core.Constant.NETWORK_TIMEOUT
import com.example.bitcointicker.core.netowrk.interceptor.ResponseControlInterceptor
import com.example.bitcointicker.data.request.NetworkService
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideNetworkService(retrofit: Retrofit): NetworkService =
        retrofit.create(NetworkService::class.java)

    @Provides
    @Singleton
    fun providesRetrofit(
        client: OkHttpClient, gsonConverterFactory: GsonConverterFactory,
    ): Retrofit {
        return Retrofit.Builder().baseUrl(Constant.API_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun providesInterceptor(): Interceptor {
        return Interceptor { chain ->
            val url = chain.request().url.newBuilder()
                .addQueryParameter("", "")
                .build()
            val request = chain.request()
                .newBuilder().url(url).build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideResponseControlInterceptor(
        gson: Gson,
        @ApplicationContext context: Context
    ): ResponseControlInterceptor =
        ResponseControlInterceptor(gson, context)

    @Provides
    @Singleton
    fun providesOkHttpClient(
        interceptor: Interceptor,
        logginInterceptor: HttpLoggingInterceptor,
        responseControlInterceptor: ResponseControlInterceptor,
        cache: Cache
    ): OkHttpClient {
        val client = OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(interceptor)
            .addInterceptor(logginInterceptor)
            .addInterceptor(responseControlInterceptor)
            .connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
        return client.build()
    }

    @Provides
    @Singleton
    fun providesOkhttpCache(@ApplicationContext context: Context): Cache {
        return Cache(context.cacheDir, MAX_MEMORY_CACHE)
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }
}
