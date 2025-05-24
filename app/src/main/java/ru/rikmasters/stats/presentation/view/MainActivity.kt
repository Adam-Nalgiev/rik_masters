package ru.rikmasters.stats.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.rikmasters.stats.presentation.view.theme.RikstatsTheme
import ru.rikmasters.viewers_list_impl.presentation.view.ViewersList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RikstatsTheme {
                Box(Modifier.Companion.fillMaxSize()) {
                    ViewersList(Modifier.align(Alignment.Center))
                }
            }
        }
    }
}