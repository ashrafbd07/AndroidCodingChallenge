package com.example.otchallenge.di

import com.example.excercise.models.network.RemoteApi
import com.example.otchallenge.model.network.FetchBookListUseCase
import com.example.otchallenge.presenter.BookPresenter
import com.example.otchallenge.utils.NetworkChecker
import com.example.otchallenge.views.ActivityScope
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class ActivityModule() {

    @Provides
    @ActivityScope
    fun provideBookPresenter(fetchBookListUseCase: FetchBookListUseCase, dispatcher: CoroutineDispatcher): BookPresenter {
        return BookPresenter(fetchBookListUseCase, dispatcher)
    }

    @Provides
    @ActivityScope
    fun provideFetchBookListUseCase(remoteApi: RemoteApi, networkChecker: NetworkChecker): FetchBookListUseCase {
        return FetchBookListUseCase(remoteApi, networkChecker)
    }
}
