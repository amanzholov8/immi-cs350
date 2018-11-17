package com.immi.thebrogrammers.immi

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_compare_cities.*

class CompareCities : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_compare_cities)
    val spinner: Spinner = findViewById<Spinner>(R.id.indexSpinner)
// Create an ArrayAdapter using the string array and a default spinner layout
    ArrayAdapter.createFromResource(
      this,
      R.array.indexArray,
      android.R.layout.simple_spinner_item
    ).also { adapter ->
      // Specify the layout to use when the list of choices appears
      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
      // Apply the adapter to the spinner
      spinner.adapter = adapter
    }
  }

  fun compareTwoCities(view: View) {

    val cityInput1: EditText = findViewById<EditText>(R.id.inputCity1)
    val cityInput2: EditText = findViewById<EditText>(R.id.inputCity2)
    val cityName1 = cityInput1.text.toString()
    val cityName2 = cityInput2.text.toString()
    println(cityName1)
    println(cityName2)
    val spinner: Spinner = findViewById<Spinner>(R.id.indexSpinner)
    var textSpinner = indexSpinner.selectedItem.toString()
    val indMap = mapOf("Healthcare quality" to "health_care_index",
      "Cost of groceries" to "groceries_index", "Pollution level" to "pollution_index",
      "Crime level" to "crime_index", "Traffic" to "traffic_index")
    val qindexString = indMap[textSpinner]
    //textCity1.text = city1
    //textCity2.text = city2
    val city1 = db.getCityByName(cityName1)
    val city2 = db.getCityByName(cityName2)
    val qindex = db.getQIndexByName(qindexString!!)
    val ans = city1?.compareCities(city2!!, qindex!!)
    answerText.text = ans
  }
}
