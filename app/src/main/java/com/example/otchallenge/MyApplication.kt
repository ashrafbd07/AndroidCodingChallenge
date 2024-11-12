package com.example.otchallenge

import android.app.Application
import com.example.otchallenge.di.AppComponent
import com.example.otchallenge.di.AppModule
import com.example.otchallenge.di.DaggerAppComponent
import com.example.otchallenge.di.NetworkModule

class MyApplication : Application() {

	lateinit var appComponent: AppComponent

	override fun onCreate() {
		super.onCreate()
		appComponent = DaggerAppComponent
			.builder()
			.appModule(AppModule(this))
			.networkModule(NetworkModule())
			.build()
	}
}
