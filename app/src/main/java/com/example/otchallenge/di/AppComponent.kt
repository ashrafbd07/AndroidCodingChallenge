package com.example.otchallenge.di

import com.example.excercise.models.network.RemoteApi
import com.example.otchallenge.utils.NetworkChecker
import com.example.otchallenge.views.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}
