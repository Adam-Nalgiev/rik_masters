package ru.rikmasters.stats.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import ru.rikmasters.stats.presentation.view.screen.MainScreen
import ru.rikmasters.stats.presentation.view.theme.RikstatsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RikstatsTheme {
                Box(Modifier.fillMaxSize()) {
                    MainScreen(modifier = Modifier)
                }
            }
        }
    }
}