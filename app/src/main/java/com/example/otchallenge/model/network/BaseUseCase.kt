package com.example.excercise.models.network

import com.example.otchallenge.utils.NetworkChecker
import retrofit2.Response

abstract class BaseUseCase(private val checker: NetworkChecker) {

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        if (checker.isConnected()) {
            try {
                val response = apiCall()
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        return NetworkResult.Success(body)
                    }
                }
                return error("${response.code()} ${response.message()}")
            } catch (e: Exception) {
                return error(e.message ?: e.toString())
            }
        }
        return error("No internet connection !")
    }

    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error("Network call failed : $errorMessage")

}