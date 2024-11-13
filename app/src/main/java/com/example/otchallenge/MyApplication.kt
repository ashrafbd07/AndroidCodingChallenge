package com.example.otchallenge

import android.app.Application
import com.example.otchallenge.di.ActivityComponent
import com.example.otchallenge.di.ActivityModule

import com.example.otchallenge.di.AppComponent
import com.example.otchallenge.di.DaggerActivityComponent
import com.example.otchallenge.di.DaggerAppComponent
import com.example.otchallenge.di.NetworkModule

class MyApplication : Application() {

	lateinit var appComponent: AppComponent

	override fun onCreate() {
		super.onCreate()

		appComponent = DaggerAppComponent
			.builder()
			.networkModule(NetworkModule(this))
			.build()
	}

	fun createActivityComponent(): ActivityComponent {
		return DaggerActivityComponent
			.builder()
			.appComponent(appComponent)
			.activityModule(ActivityModule())
			.build()
	}
}
