package com.example.otchallenge.model

import com.example.otchallenge.model.network.FetchBookListUseCase
import com.example.excercise.models.network.RemoteApi
import com.example.otchallenge.MockData
import com.example.otchallenge.utils.NetworkChecker
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class FetchBookListUseCaseTest {

    private lateinit var fetchBookListUseCase: FetchBookListUseCase

    @Mock
    lateinit var mockRemoteApi: RemoteApi

    @Mock
    lateinit var networkChecker: NetworkChecker

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        fetchBookListUseCase = FetchBookListUseCase(mockRemoteApi, networkChecker)
    }

    @Test
    fun `execute should return success when API call is successful`() = runTest {

        Mockito.`when`(networkChecker.isConnected()).thenReturn(true)
        val mockBookResponse = MockData.createMockResponse()
        Mockito.`when`(mockRemoteApi.getBookList(Mockito.anyString(), Mockito.anyInt()))
            .thenReturn(Response.success(mockBookResponse))

        val result = fetchBookListUseCase.execute()

        assert(result.data?.results?.books?.let { it.size == 1 } == true) {
            "Expected at least 1 book, but got: ${result.data?.results?.books?.size ?: "null"}"
        }
    }

    @Test
    fun `execute should return failre when network is not connected`() = runTest {

        Mockito.`when`(networkChecker.isConnected()).thenReturn(false)
        val result = fetchBookListUseCase.execute()

        verify(mockRemoteApi, never()).getBookList(Mockito.anyString(), Mockito.anyInt())

        assert(result.data?.results == null)
    }
}