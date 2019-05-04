package com.arseniy.cryptoid.presentation.details.chart

import android.content.Context
import android.graphics.Color
import com.arseniy.cryptoid.R
import com.arseniy.cryptoid.domain.history.HistoricalData
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import kotlinx.android.synthetic.main.chart_marker.view.*
import java.text.SimpleDateFormat
import java.util.*

const val CHART_LABEL = ""

fun LineChart.setUp(historicalData: List<HistoricalData>) {
    if (historicalData.isNullOrEmpty()) {
        setNoDataText(context.getString(R.string.error_message))
        setNoDataTextColor(R.color.colorPrimaryDark)
        getPaint(Chart.PAINT_INFO).textSize = 64f
        return
    }
    data = lineDataFrom(historicalData)
    legend.isEnabled = false
    description.text = ""
    marker = CustomMarkerView(
        this.context,
        R.layout.chart_marker,
        medianTimestamp(historicalData)
    )
    with(xAxis) {
        position = BOTTOM
        setDrawGridLines(false)
        valueFormatter = XAxisValueFormatter()
    }
    with(axisLeft) {
        setDrawAxisLine(false)
        setDrawGridLines(false)
    }
    with(axisRight) {
        setDrawAxisLine(false)
        setDrawGridLines(false)
        setDrawLabels(false)
    }
    invalidate()
}

private fun lineDataFrom(data: List<HistoricalData>) = LineData(
    LineDataSet(
        data.map { Entry(it.timestamp.toFloat(), it.price.toFloat()) },
        CHART_LABEL
    ).apply {
        color = Color.GRAY
        fillColor = Color.GRAY
        highLightColor = Color.GRAY
        setDrawHorizontalHighlightIndicator(false)
        setDrawFilled(true)
        setDrawValues(false)
        setDrawCircles(false)
    }
)

private fun medianTimestamp(data: List<HistoricalData>) = data[data.size / 2].timestamp.toFloat()

private class CustomMarkerView(context: Context, layoutResource: Int, private val medianTimestamp: Float)
    : MarkerView(context, layoutResource) {

    private var offsetPoint = MPPointF.getInstance(-width.toFloat(), -height.toFloat() / 2)

    private var wasLeft = false
    override fun refreshContent(entry: Entry, highlight: Highlight?) {
        tvMarker.text = "Close: ${entry.y.toString()}"
        if (entry.x < medianTimestamp) {
            if (!wasLeft) {
                changeOrientation(180f, 1)
            }
        } else {
            if (wasLeft) {
                changeOrientation(0f, -1)
            }
        }
        super.refreshContent(entry, highlight)
    }

    private fun changeOrientation(rotation: Float, xOffsetFactor: Int) {
        wasLeft = !wasLeft
        ivMarker.rotation = rotation
        offsetPoint.x += width.toFloat() * xOffsetFactor
    }

    override fun getOffsetForDrawingAtPoint(posX: Float, posY: Float): MPPointF = offsetPoint
}

private class XAxisValueFormatter : IndexAxisValueFormatter() {

    companion object {
        const val TIMESTAMP_FORMAT = "dd MMM"
        const val MILLISECONDS = 1000
    }

    override fun getFormattedValue(value: Float): String =
        SimpleDateFormat(
            TIMESTAMP_FORMAT,
            Locale.US
        ).format(Date(value.toLong() * MILLISECONDS))
}
