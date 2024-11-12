package com.example.otchallenge

import com.example.otchallenge.model.data.Book
import com.example.otchallenge.model.data.BookResponse
import com.example.otchallenge.model.data.BookResults

object MockData {
    fun createMockResponse(): BookResponse {
        val bookItem = Book(
            title = "Title Test",
            description = "Some description",
            bookImage = "https://someURL.com",
            bookUri = "mock:book:uri"
        )

        val bookItems = BookResults(
            displayName = "Some Book Name",
            books = listOf(bookItem)
        )

        return BookResponse(
            status = "OK",
            results = bookItems
        )
    }
}