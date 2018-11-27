package com.immi.thebrogrammers.immi

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.ValueDataEntry

class TrafficFragment : Fragment() {
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_traffic, null)
    //val searchView = inflater.inflate(R.layout.activity_main, null)
    //val cityName = searchView.findViewById<EditText>(R.id.searchBar).getText().toString()
    val cityName = arguments!!.getString("GEO_OBJECT_NAME")
    val tmp = ImmIParser.getTrafficSubQIndices(cityName!!).toList()
    val dataList = tmp.subList(3, 10)
    val pieChartView = view.findViewById<com.anychart.AnyChartView>(R.id.pie_chart_view_traffic)
    val pie = AnyChart.pie()
    pie.data(dataList.map({ c -> ValueDataEntry(c.first, c.second) }))
    pie.title("$cityName: Transport means surveyed")
    pieChartView.setChart(pie)
    return view
  }
}