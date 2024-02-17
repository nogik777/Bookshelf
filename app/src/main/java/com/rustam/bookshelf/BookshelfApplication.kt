package com.rustam.bookshelf

import android.app.Application
import com.rustam.bookshelf.data.AppContainer
import com.rustam.bookshelf.data.DefaultAppContainer

class BookshelfApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
