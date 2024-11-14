package com.example.otchallenge.presenter

import android.util.Log
import com.example.excercise.models.network.NetworkResult
import com.example.otchallenge.model.data.Book
import com.example.otchallenge.model.network.FetchBookListUseCase
import com.example.otchallenge.views.ViewHolderBook
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class BookPresenter @Inject constructor(private val fetchBookUseCase: FetchBookListUseCase,
                                        private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    var bookList: List<Book>? = null

    fun fetchBookList(view: ViewHolderBook) {
        bookList?.let {
            view.showData(it)
        }

        view.showLoading(true)

        CoroutineScope(dispatcher).launch {
            try {
                val result = fetchBookUseCase.execute()
                withContext(Dispatchers.Main) {
                    when (result) {
                        is NetworkResult.Success -> {
                            result.data?.results?.books?.let {
                                bookList = it
                                view.showData(it)
                            }
                        }
                        is NetworkResult.Error -> {
                            view.showError(result.message)
                        }
                    }
                }
            } catch (e: Exception) {
                view.showError()
            }
        }
    }
}