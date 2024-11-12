package com.example.otchallenge.presenter

import com.example.otchallenge.model.network.FetchBookListUseCase
import com.example.excercise.models.network.NetworkResult
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

    fun fetchBookList(view: ViewHolderBook) {
        view.showLoading(true)

        CoroutineScope(dispatcher).launch {
            try {
                val result = fetchBookUseCase.execute()
                withContext(Dispatchers.Main) {
                    when (result) {
                        is NetworkResult.Success -> {
                            result.data?.results?.books?.let {
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