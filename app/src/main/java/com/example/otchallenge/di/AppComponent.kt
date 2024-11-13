package com.example.otchallenge.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {
    fun activityComponent(): ActivityComponent.Factory
}
