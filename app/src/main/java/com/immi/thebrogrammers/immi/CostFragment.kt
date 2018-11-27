package com.immi.thebrogrammers.immi

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView

class CostFragment : Fragment() {
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_cost, null)
    val cityName = arguments!!.getString("GEO_OBJECT_NAME")
    val dataList = ImmIParser.getGroceriesSubQIndices(cityName!!).toList()
    var scroll: ListView = view.findViewById<ListView>(R.id.price_view)
    scroll.adapter = ArrayAdapter<String>(
      activity,
      android.R.layout.simple_list_item_1,
      dataList.map({ c -> "%s - %.2f".format(c.first, c.second) }))
    return view
  }
}