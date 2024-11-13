package com.example.otchallenge.di

import com.example.excercise.models.network.RemoteApi
import com.example.otchallenge.utils.NetworkChecker
import dagger.Component
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {
    fun remoteApi(): RemoteApi
    fun networkChecker(): NetworkChecker
    fun provideIoDispatcher(): CoroutineDispatcher
}
