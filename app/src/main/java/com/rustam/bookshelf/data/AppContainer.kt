package com.rustam.bookshelf.data


import com.rustam.bookshelf.network.BookshelfApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


interface AppContainer {
    val bookshelfDataRepository: BookshelfDataRepository
}

class DefaultAppContainer : AppContainer {

    private val baseUrl = "https://www.googleapis.com"





    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: BookshelfApiService by lazy {
        retrofit.create(BookshelfApiService::class.java)
    }

    override val bookshelfDataRepository: BookshelfDataRepository by lazy {
        NetworkBookshelfDataRepository(retrofitService)
    }
}