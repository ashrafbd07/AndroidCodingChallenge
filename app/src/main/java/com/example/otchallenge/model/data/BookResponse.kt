package com.example.otchallenge.model.data

import android.os.Parcel
import android.os.Parcelable
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
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(bookImage)
        parcel.writeString(bookUri)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }
}