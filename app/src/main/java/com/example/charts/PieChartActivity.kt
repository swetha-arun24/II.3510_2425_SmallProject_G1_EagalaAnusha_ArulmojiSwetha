package com.example.charts


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import android.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class PieChartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up the PieChart
        val pieChart = PieChart(this)

        // Sample data for the PieChart
        val entries = listOf(
            PieEntry(30f, "Category 1"),
            PieEntry(20f, "Category 2"),
            PieEntry(50f, "Category 3")
        )
        val dataSet = PieDataSet(entries, "Sample Pie Chart")
        dataSet.colors= listOf(Color.RED ,Color.BLUE,Color.GREEN)
        val data = PieData(dataSet)

        pieChart.data = data

        // Set up the PieChart in Jetpack Compose using AndroidView
        setContent {
            AndroidView(
                factory = { pieChart },
                modifier = Modifier.fillMaxSize() // Use Modifier to define the layout
            )
        }
    }
}
