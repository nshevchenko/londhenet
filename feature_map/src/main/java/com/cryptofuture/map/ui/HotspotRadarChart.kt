package com.cryptofuture.map.ui

import android.content.Context
import android.graphics.DashPathEffect
import androidx.core.content.ContextCompat
import com.cryptofuture.map.R
import com.cryptofuture.map.model.PinUI
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

fun customiseChart(context: Context, chart: RadarChart, hotspot: PinUI) {
    val lightGray = ContextCompat.getColor(context, R.color.light_gray)
    val colorFill = ContextCompat.getColor(context, R.color.purple_chart)
    val colorLine = ContextCompat.getColor(context, R.color.purple_200)
    val padding = context.resources.getDimension(R.dimen.spacing_4)
    val labels = listOf("N", "NE", "E", "SE", "S", "SW", "W", "NW")
    val datatSet =
        RadarDataSet(hotspot.directions.map { RadarEntry(it * 5.0F) }, "")
            .apply {
                setDrawValues(false)
                lineWidth = 3f
                color = colorLine
                setDrawFilled(true)
                fillColor = colorFill
                fillAlpha = 100
            }

    chart.apply {
        setExtraOffsets(padding, padding, padding, padding)
        webAlpha = 100
        skipWebLineCount = 3
        setTouchEnabled(false)
        xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        xAxis.setLabelCount(8, true)
        xAxis.textColor = lightGray
        yAxis.setLabelCount(8, true)
        yAxis.setDrawLabels(false)
        legend.apply {
            isEnabled = false
        }
        description.isEnabled = false
        isRotationEnabled = false
        data = RadarData(datatSet)
        chart.animateXY(400, 400, Easing.EaseInOutQuad)
        invalidate()
    }
}