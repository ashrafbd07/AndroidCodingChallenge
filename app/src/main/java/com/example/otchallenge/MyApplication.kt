package com.example.otchallenge

import android.app.Application

import com.example.otchallenge.di.NetworkComponent
import com.example.otchallenge.di.DaggerNetworkComponent
import com.example.otchallenge.di.NetworkModule

class MyApplication : Application() {

	lateinit var networkComponent: NetworkComponent

	override fun onCreate() {
		super.onCreate()

		networkComponent = DaggerNetworkComponent
			.builder()
			.networkModule(NetworkModule(this))
			.build()
	}
}
