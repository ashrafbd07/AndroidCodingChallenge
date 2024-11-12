package com.example.otchallenge.presenter

import com.example.otchallenge.model.network.FetchBookListUseCase
import com.example.excercise.models.network.NetworkResult
import com.example.otchallenge.MockData
import com.example.otchallenge.views.ViewHolderBook
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class BookPresenterTest {

    private lateinit var presenter: BookPresenter
    private lateinit var fetchBookListUseCase: FetchBookListUseCase

    // Use to control coroutines during tests
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var testScope: TestScope


    @Before
    fun setUp() {
        fetchBookListUseCase = mock(FetchBookListUseCase::class.java)
        presenter = BookPresenter(fetchBookListUseCase, testDispatcher)
        testScope = TestScope(testDispatcher)

        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        // Clean up the main dispatcher after the tests
        Dispatchers.resetMain()
    }

    @Test
    fun  `fetchBookList should show data on success`() = testScope.runTest() {
        val response = MockData.createMockResponse()
        `when`(fetchBookListUseCase.execute()).thenReturn(NetworkResult.Success(response))
        var mockView = mock(ViewHolderBook::class.java)
        presenter.fetchBookList(mockView)

        advanceUntilIdle()

        verify(mockView).showLoading(true)
        verify(mockView).showData(response.results.books)
        verifyNoMoreInteractions(mockView)
    }

    @Test
    fun  `fetchBookList should show error on failure`() = runTest(testDispatcher) {
        val errorMessage = "Error fetching books"
        `when`(fetchBookListUseCase.execute()).thenReturn(NetworkResult.Error(errorMessage))
        var mockView = mock(ViewHolderBook::class.java)
        presenter.fetchBookList(mockView)

        advanceUntilIdle()

        verify(mockView).showLoading(true)
        verify(mockView).showError(errorMessage)
        verifyNoMoreInteractions(mockView)
    }

    @Test
    fun `fetchBookList should show generic error on exception`() = runTest(testDispatcher) {

        val errorMessage = "Some exception error"
        `when`(fetchBookListUseCase.execute()).thenThrow(RuntimeException(errorMessage))

        var mockView = mock(ViewHolderBook::class.java)
        presenter.fetchBookList(mockView)

        advanceUntilIdle()

        verify(mockView).showLoading(true)
        verify(mockView).showError()
    }
}