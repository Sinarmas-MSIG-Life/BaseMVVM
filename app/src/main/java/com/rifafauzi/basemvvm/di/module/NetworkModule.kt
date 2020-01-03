package com.rifafauzi.basemvvm.di.module

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rifafauzi.basemvvm.BuildConfig
import com.rifafauzi.basemvvm.utils.DEFAULT_CONNECT_TIMEOUT
import com.rifafauzi.basemvvm.utils.DEFAULT_READ_TIMEOUT
import com.rifafauzi.basemvvm.utils.DEFAULT_WRITE_TIMEOUT
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by rrifafauzikomara on 2020-01-03.
 */

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpCache(application: Application) : Cache {
        val cacheSize : Long = 10 * 10 * 1024
        return Cache(application.cacheDir, cacheSize)
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor() : HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor, cache: Cache, authInterceptor: Interceptor) : OkHttpClient {
        val client = OkHttpClient.Builder()
        client.connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
        client.writeTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
        client.readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.MILLISECONDS)
        client.cache(cache)
        client.addInterceptor(loggingInterceptor)
        client.addInterceptor(authInterceptor)
        return client.build()
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(): Interceptor {

        return Interceptor { chain ->

            val request = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()

            val newRequest = chain.request()
                .newBuilder()
                .url(request)
                .build()

            chain.proceed(newRequest)
        }
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().serializeNulls().create()
    }

    @Provides
    @Singleton
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, okHttpClient: OkHttpClient, gson: Gson) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

}