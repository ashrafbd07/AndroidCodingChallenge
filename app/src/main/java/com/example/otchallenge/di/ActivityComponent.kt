package com.example.otchallenge.di

import com.example.otchallenge.views.ActivityScope
import com.example.otchallenge.views.MainActivity
import dagger.Component

@ActivityScope
@Component(modules = [ActivityModule::class], dependencies = [AppComponent::class])
interface ActivityComponent {
	fun inject(activity: MainActivity)
}
