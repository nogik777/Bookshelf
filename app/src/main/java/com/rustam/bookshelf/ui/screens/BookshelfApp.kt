package com.rustam.bookshelf.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rustam.bookshelf.R
import com.rustam.bookshelf.ui.BookshelfViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookshelfApp(modifier: Modifier = Modifier) {
    val bookshelfViewModel: BookshelfViewModel = viewModel(factory = BookshelfViewModel.Factory)
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar (
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                }
            )
        }

    ) {
        Surface(modifier = modifier
            .fillMaxSize()
            .padding(it),
        ) {
            HomeScreen(
                bookshelfUiState = bookshelfViewModel.bookshelfUiState,
                modifier = modifier
            )
        }
    }
}