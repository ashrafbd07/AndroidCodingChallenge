package com.example.otchallenge.di

import android.app.Application
import com.example.excercise.models.network.RemoteApi
import com.example.otchallenge.utils.Constants
import com.example.otchallenge.utils.NetworkChecker
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule() {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(provideHttpLoggingInterceptor())
            .build()
    }

    fun  provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun remoteApi(retrofit: Retrofit): RemoteApi {
        return retrofit.create(RemoteApi::class.java)
    }

    @Provides
    @Singleton
    fun networkChecker(application: Application): NetworkChecker {
        return NetworkChecker(application)
    }

}
