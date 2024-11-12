package com.example.charts

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.charts.ui.theme.ChartsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChartsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ChartSelectionScreen(
                        modifier = Modifier.padding(innerPadding),
                        onPieChartClick = { startActivity(Intent(this, PieChartActivity::class.java)) },
                        onBarChartClick = { startActivity(Intent(this, BarChartActivity::class.java)) },
                        onLineChartClick = { startActivity(Intent(this, LineChartActivity::class.java)) }
                    )
                }
            }
        }
    }
}

@Composable
fun ChartSelectionScreen(
    modifier: Modifier = Modifier,
    onPieChartClick: () -> Unit,
    onBarChartClick: () -> Unit,
    onLineChartClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Select the Chart:",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Button(onClick = onPieChartClick) {
            Text(text = "Pie Chart")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onBarChartClick) {
            Text(text = "Bar Chart")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onLineChartClick) {
            Text(text = "Line Chart")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChartSelectionScreenPreview() {
    ChartsTheme {
        ChartSelectionScreen(
            onPieChartClick = {},
            onBarChartClick = {},
            onLineChartClick = {}
        )
    }
}
