package com.example.otchallenge.model.data

import com.google.gson.annotations.SerializedName

data class BookResponse(
    @SerializedName("status") val status: String,
    @SerializedName("results") val results: BookResults
)

data class BookResults(
    @SerializedName("display_name") val displayName: String,
    @SerializedName("books") val books: List<Book>
)

data class Book(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("book_image") val bookImage: String,
    @SerializedName("book_uri") val bookUri: String
)