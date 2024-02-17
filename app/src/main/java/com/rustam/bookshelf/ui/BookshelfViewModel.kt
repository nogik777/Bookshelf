package com.rustam.bookshelf.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.rustam.bookshelf.BookshelfApplication
import com.rustam.bookshelf.data.Book
import com.rustam.bookshelf.data.BookshelfDataRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface BookshelfUiState {
    data class Success(val data: List<Book>) : BookshelfUiState
    data object Error : BookshelfUiState
    data object Loading : BookshelfUiState
}

class BookshelfViewModel(private val bookshelfDataRepository: BookshelfDataRepository) :
    ViewModel() {

    var bookshelfUiState: BookshelfUiState by mutableStateOf(BookshelfUiState.Loading)
        private set

    init {
        getData()
    }

    fun getData(query: String = "book", maxResults: Int = 40) {
        viewModelScope.launch {
            bookshelfUiState = try {
                BookshelfUiState.Success(bookshelfDataRepository.getBooks(query, maxResults))
            } catch (e: IOException) {
                BookshelfUiState.Error
            } catch (e: HttpException) {
                BookshelfUiState.Error
            }
        }
    }

    companion object {
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookshelfApplication)
                val bookshelfDataRepository = application.container.bookshelfDataRepository
                BookshelfViewModel(bookshelfDataRepository = bookshelfDataRepository)
            }
        }
    }
}