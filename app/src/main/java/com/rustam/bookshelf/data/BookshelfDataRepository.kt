package com.rustam.bookshelf.data

import com.rustam.bookshelf.network.BookshelfApiService

interface BookshelfDataRepository {

    suspend fun getBooks(query: String, maxResult: Int): List<Book>

}

class NetworkBookshelfDataRepository(
    private val bookshelfApiService: BookshelfApiService
) : BookshelfDataRepository {
    override suspend fun getBooks(
        query: String,
        maxResult: Int
    ): List<Book> = bookshelfApiService.bookSearch(query, maxResult).items.map { items ->
        Book(
            title = items.volumeInfo?.title,
            previewLink = items.volumeInfo?.previewLink,
            imageLink = items.volumeInfo?.imageLinks?.thumbnail
        )
    }
}