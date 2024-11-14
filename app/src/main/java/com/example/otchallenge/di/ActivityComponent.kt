package com.example.otchallenge.di

import com.example.otchallenge.views.MainActivity
import dagger.Subcomponent

@Subcomponent
interface ActivityComponent {
	fun inject(activity: MainActivity)

	@Subcomponent.Factory
	interface Factory {
		fun create(): ActivityComponent
	}
}
