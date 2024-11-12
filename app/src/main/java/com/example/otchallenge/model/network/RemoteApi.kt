package com.example.excercise.models.network

import com.example.otchallenge.model.data.BookResponse
import com.example.otchallenge.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApi {

    /**
     * Fetches the list of published books from the remote server.
     *
     * @param apiKey The API key required to authenticate the request.
     * @param offset The offset for pagination. It specifies the starting point for the list of books to be retrieved.
     * @return A [Response] containing a [BookResponse] object.
     */
    @GET(Constants.BOOK_LIST_URL)
    suspend fun getBookList(
        @Query("api-key") apiKey: String,
        @Query("offset") offset: Int
    ): Response<BookResponse>
}