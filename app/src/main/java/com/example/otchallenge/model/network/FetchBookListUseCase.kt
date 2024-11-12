package com.example.otchallenge.model.network

import com.example.excercise.models.network.BaseUseCase
import com.example.excercise.models.network.NetworkResult
import com.example.excercise.models.network.RemoteApi
import com.example.otchallenge.BuildConfig
import com.example.otchallenge.model.data.BookResponse
import com.example.otchallenge.utils.NetworkChecker
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FetchBookListUseCase @Inject constructor(
    private val remoteApi: RemoteApi,
    checker: NetworkChecker
) : BaseUseCase(checker) {

    suspend fun execute(): NetworkResult<BookResponse> {
        return safeApiCall { remoteApi.getBookList(BuildConfig.API_KEY, 0) }
    }
}