package com.rustam.bookshelf.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rustam.bookshelf.data.Book
import com.rustam.bookshelf.ui.BookshelfViewModel
import com.rustam.bookshelf.ui.MainAppBar
import com.rustam.bookshelf.ui.SearchWidgetState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookshelfApp(
    modifier: Modifier = Modifier,
    onBookClicked: (Book) -> Unit
) {
    val bookshelfViewModel: BookshelfViewModel = viewModel(factory = BookshelfViewModel.Factory)
    val searchWidgetState = bookshelfViewModel.searchWidgetState
    val searchTextState = bookshelfViewModel.searchTextState
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            MainAppBar(
                searchWidgetState = searchWidgetState.value,
                searchTextState = searchTextState.value,
                onTextChange = { bookshelfViewModel.updateSearchTextState(it) },
                onCloseClicked = { bookshelfViewModel.updateSearchWidgetState(SearchWidgetState.CLOSED) },
                onSearchClicked = { bookshelfViewModel.getData(it) },
                onSearchTriggered = { bookshelfViewModel.updateSearchWidgetState(SearchWidgetState.OPENED) }
            )
        }

    ) {
        Surface(modifier = modifier
            .fillMaxSize()
            .padding(it),
        ) {
            HomeScreen(
                bookshelfUiState = bookshelfViewModel.bookshelfUiState,
                modifier = modifier,
                onBookClicked,
            )
        }
    }
}