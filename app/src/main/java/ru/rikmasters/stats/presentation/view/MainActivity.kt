package ru.rikmasters.stats.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.rikmasters.circular_chart_impl.presentation.view.GenderCircularChart
import ru.rikmasters.stats.presentation.view.theme.RikstatsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RikstatsTheme {
                Box(Modifier.Companion.fillMaxSize()) {
                    GenderCircularChart(
                        modifier = Modifier.Companion.align(Alignment.Companion.Center),
                        200.dp
                    )
                }
            }
        }
    }
}