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
import com.anychart.enums.Anchor
import com.anychart.enums.HoverMode
import com.anychart.enums.TooltipDisplayMode
import com.anychart.enums.TooltipPositionMode

class CostFragment : Fragment() {
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_cost, null)
    //val searchView = inflater.inflate(R.layout.activity_main, null)
    //val cityName = searchView.findViewById<EditText>(R.id.searchBar).getText().toString()
    val cityName = arguments!!.getString("GEO_OBJECT_NAME")
    val dataList = ImmIParser.getGroceriesSubQIndices(cityName!!).toList()
    val data: List<ValueDataEntry> = dataList.map({ c -> ValueDataEntry(c.first, c.second.toInt()) })
    val anyChartView = view.findViewById<com.anychart.AnyChartView>(R.id.any_chart_view_cost)
    val barChart: Cartesian = AnyChart.bar()

    barChart.animation(true)
    barChart.padding(10, 20, 5, 20)

    barChart.yAxis(0).labels().format(
      "function() {\n" +
        "    return Math.abs(this.value).toLocaleString() + \"%\";\n" +
        "  }")

    barChart.yAxis(0)
    barChart.yScale().maximum(100)
    barChart.yScale().minimum(0)
    //barChart.xAxis(0).overlapMode(LabelsOverlapMode.ALLOW_OVERLAP)
    barChart.xAxis(0).labels().width(150)
    //barChart.pointWidth(100)
    barChart.title("$cityName: Component of cost of living surveyed")
    barChart.barsPadding(10)
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
          "      return '<span style=\"color: #D9D9D9\">%</span>' + Math.abs(this.value).toLocaleString();\n" +
          "    }")
    barChart.xScroller(true)
    barChart.legend().enabled(true)
    barChart.legend().inverted(true)
    barChart.legend().fontSize(13.0)
    barChart.legend().padding(0.0, 0.0, 20.0, 0.0)
    val seriesData = mutableListOf<DataEntry>()
    seriesData.add(ValueDataEntry("Nail polish", 20))
    val series1 = barChart.bar(data)
    series1.name("Satisfaction %")
    series1.tooltip()
      .position("right")
      .anchor(Anchor.LEFT_CENTER)


    anyChartView.setChart(barChart)

    return view
  }
}