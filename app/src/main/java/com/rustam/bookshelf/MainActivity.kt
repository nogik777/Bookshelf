package com.rustam.bookshelf

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.rustam.bookshelf.ui.screens.BookshelfApp
import com.rustam.bookshelf.ui.theme.BookshelfTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookshelfTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    BookshelfApp(
                        onBookClicked = {
                            ContextCompat.startActivity(
                                this,
                                Intent(Intent.ACTION_VIEW, Uri.parse(it.previewLink)),
                                null
                            )
                        }
                    )
                }
            }
        }
    }
}
