package com.rustam.bookshelf.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.rustam.bookshelf.R

@Composable
fun MainAppBar(
    searchWidgetState: SearchWidgetState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit
) {
    when (searchWidgetState) {
        SearchWidgetState.CLOSED -> {
            ClosedAppBar (
                onSearchClicked = onSearchTriggered
            )
        }
        SearchWidgetState.OPENED -> {
            OpenedAppBar(
                text = searchTextState,
                onTextChange = onTextChange,
                onCloseClicked = onCloseClicked,
                onSearchClicked = onSearchClicked
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClosedAppBar(onSearchClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.app_name))
        },
        actions = {
            IconButton(onClick = { onSearchClicked() }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "SearchIcon",
                    tint = Color.White
                )
            }
        }
    )
}

@Composable
fun OpenedAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        color = MaterialTheme.colorScheme.onPrimary
    ) {
        TextField(
            value = text,
            onValueChange = { onTextChange(it) },
            placeholder = {
                          Text(
                              text = "Search here...",
                              color = Color.White,
                              modifier = Modifier.alpha(DefaultAlpha))
            },
            singleLine = true,
            leadingIcon = {
                          IconButton(
                              onClick = { onSearchClicked(text) },
                              modifier = Modifier.alpha(DefaultAlpha)
                              ) {
                              Icon(
                                  imageVector = Icons.Default.Search,
                                  contentDescription = "Search icon",
                                  tint = Color.White
                              )
                          }
            },
            trailingIcon = {
                           IconButton(
                               onClick = {
                                   if (text.isNotEmpty()) {
                                       onTextChange("")
                                   } else {
                                       onCloseClicked()
                                   }
                               }
                           ) {
                               Icon(
                                   imageVector = Icons.Default.Close,
                                   contentDescription = "Close Icon",
                                   tint = Color.White
                               )
                           }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = { onSearchClicked(text) }
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}