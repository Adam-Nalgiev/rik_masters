package ru.rikmasters.stats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.rikmasters.line_chart_impl.presentation.view.ViewsChart
import ru.rikmasters.stats.ui.theme.RikstatsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RikstatsTheme {
                Box(Modifier.fillMaxSize()) {
                    ViewsChart(
                        Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}