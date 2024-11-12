package com.example.charts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.Entry
import androidx.compose.ui.viewinterop.AndroidView

class LineChartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up the LineChart
        val lineChart = LineChart(this)

        // Sample data for the LineChart
        val entries = listOf(
            Entry(1f, 10f),
            Entry(2f, 20f),
            Entry(3f, 100f),
            Entry(4f, 2f),
            Entry(5f, 15f)
        )
        val dataSet = LineDataSet(entries, "Sample Line Chart")
        val data = LineData(dataSet)

        lineChart.data = data
        setContent {
            AndroidView({ lineChart }, modifier = Modifier.fillMaxSize())
        }
    }
}
