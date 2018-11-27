package com.immi.thebrogrammers.immi


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Cartesian
import com.anychart.core.axes.Linear
import com.anychart.data.Set
import com.anychart.enums.*

/**
 * A simple [Fragment] subclass.
 *
 */
class HealthCompare : Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_health_compare, container, false)
    val anyChartView = view.findViewById<com.anychart.AnyChartView>(R.id.any_chart_view)

    val barChart: Cartesian = AnyChart.bar()

    barChart.animation(true)
    barChart.padding(10, 20, 5, 20)

    barChart.yScale().stackMode(ScaleStackMode.VALUE)

    barChart.yScale().stackMode(ScaleStackMode.VALUE)

    barChart.yAxis(0).labels().format(
      "function() {\n" +
        "    return Math.abs(this.value).toLocaleString();\n" +
        "  }")

    barChart.yAxis(0).title("Revenue in Dollars")

    barChart.xAxis(0).overlapMode(LabelsOverlapMode.ALLOW_OVERLAP)

    val xAxis1: Linear = barChart.xAxis(1)
    xAxis1.enabled(true)
    xAxis1.orientation(Orientation.RIGHT)
    xAxis1.overlapMode(LabelsOverlapMode.ALLOW_OVERLAP)

    barChart.title("Cosmetic Sales by Gender")

    barChart.interactivity().hoverMode(HoverMode.BY_X)

    barChart.tooltip()
      .title(false)
      .separator(false)
      .displayMode(TooltipDisplayMode.SEPARATED)
      .positionMode(TooltipPositionMode.POINT)
      .useHtml(true)
      .fontSize(12)
      .offsetX(5)
      .offsetY(0)
      .format(
        "function() {\n" +
          "      return '<span style=\"color: #D9D9D9\">$</span>' + Math.abs(this.value).toLocaleString();\n" +
          "    }")
    barChart.xScroller(true)
    val seriesData = mutableListOf<DataEntry>()
    seriesData.add(CustomDataEntry("Nail polish", 5376, -229))
    seriesData.add(CustomDataEntry("Eyebrow pencil", 10987, -932))
    seriesData.add(CustomDataEntry("Rouge", 7624, -5221))
    seriesData.add(CustomDataEntry("Lipstick", 8814, -256))
    seriesData.add(CustomDataEntry("Eyeshadows", 8998, -308))
    seriesData.add(CustomDataEntry("Eyeliner", 9321, -432))
    seriesData.add(CustomDataEntry("Foundation", 8342, -701))
    seriesData.add(CustomDataEntry("Lip gloss", 6998, -908))
    seriesData.add(CustomDataEntry("Mascara", 9261, -712))

    val set = Set.instantiate()
    set.data(seriesData)
    val series1Data = set.mapAs("{ x: 'x', value: 'value' }")
    val series2Data = set.mapAs("{ x: 'x', value: 'value2' }")

    val series1 = barChart.bar(series1Data)
    series1.name("Females")
      .color("HotPink")
    series1.tooltip()
      .position("right")
      .anchor(Anchor.LEFT_CENTER)

    val series2 = barChart.bar(series2Data)
    series2.name("Males")
    series2.tooltip()
      .position("left")
      .anchor(Anchor.RIGHT_CENTER)

    barChart.legend().enabled(true)
    barChart.legend().inverted(true)
    barChart.legend().fontSize(13.0)
    barChart.legend().padding(0.0, 0.0, 20.0, 0.0)

    anyChartView.setChart(barChart)

    return view
  }

  private inner class CustomDataEntry internal constructor(x: String, value: Number, value2: Number) : ValueDataEntry(x, value) {
    init {
      setValue("value2", value2)
    }
  }
}
