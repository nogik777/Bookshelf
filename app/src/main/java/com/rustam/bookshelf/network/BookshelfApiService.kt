package com.rustam.bookshelf.network


import com.rustam.bookshelf.model.BooksData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookshelfApiService {
    @GET("books/v1/volumes")
    suspend fun bookSearch(
        @Query("q") query: String,
        @Query("maxResults") maxResult: Int
    ): BooksData


}