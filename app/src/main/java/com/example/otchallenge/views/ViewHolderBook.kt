package com.example.otchallenge.views

import com.example.otchallenge.model.data.Book

interface ViewHolderBook {
    fun showLoading(isLoading : Boolean)
    fun showData(bookList: List<Book>)
    fun showError(errorMessage: String? = null)
}