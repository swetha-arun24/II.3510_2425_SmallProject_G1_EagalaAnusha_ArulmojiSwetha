package com.example.charts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

class BarChartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up the BarChart
        val barChart = BarChart(this)

        // Sample data for the BarChart
        val entries = listOf(
            BarEntry(1f, 10f),
            BarEntry(2f, 20f),
            BarEntry(3f, 30f)
        )
        val dataSet = BarDataSet(entries, "Sample Bar Chart")
        val data = BarData(dataSet)

        barChart.data = data
        setContent {
            AndroidView({ barChart }, modifier = Modifier.fillMaxSize())
        }
    }
}
