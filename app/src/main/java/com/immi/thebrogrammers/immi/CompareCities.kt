package com.immi.thebrogrammers.immi

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_compare_cities.*


class CompareCities : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_compare_cities)
    if (intent.hasExtra("GEO_OBJECT_NAME")) {
      inputCity1.setText(intent.getStringExtra("GEO_OBJECT_NAME")!!)
    }
    // Create an ArrayAdapter using the string array and a default spinner layout
    ArrayAdapter.createFromResource(
      this,
      R.array.indexArray,
      android.R.layout.simple_spinner_item
    ).also { adapter ->
      // Specify the layout to use when the list of choices appears
      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
      // Apply the adapter to the spinner
      indexSpinner.adapter = adapter
    }
    val geoNames = arrayListOf<String>()
    geoNames += ImmIDatabase.cities.map({ c -> c.geo_name }).toTypedArray()
    val adapter = ArrayAdapter<String>(
      this,
      android.R.layout.simple_list_item_1,
      geoNames)
    inputCity1.setAdapter(adapter)
    inputCity2.setAdapter(adapter)
  }

  fun compareTwoCities(view: View) {
    val cityInput1: EditText = findViewById<EditText>(R.id.inputCity1)
    val cityInput2: EditText = findViewById<EditText>(R.id.inputCity2)

    val cityName1 = cityInput1.text.toString()
    val cityName2 = cityInput2.text.toString()

    val spinnerText = indexSpinner.selectedItem.toString()
    val indMap = mapOf(
      "Healthcare quality" to "Healthcare index",
      "Cost of groceries" to "Cost of living index",
      "Pollution level" to "Pollution index",
      "Crime level" to "Crime index",
      "Traffic" to "Traffic time index")
    val qindexString = indMap[spinnerText]
    val city1 = ImmIDatabase.getCityByName(cityName1)!!
    val city2 = ImmIDatabase.getCityByName(cityName2)!!
    if (city1.qIndices == null)
      city1.qIndices = ImmIParser.getQIndices((cityName1))


    if (city2.qIndices == null)
      city2.qIndices = ImmIParser.getQIndices((cityName2))

    when (qindexString) {
      "Healthcare index" -> {
        city1.qIndices = (city1.qIndices + ImmIParser.getHealthSubQIndices((cityName1))).toMutableMap()
        city2.qIndices = (city2.qIndices + ImmIParser.getHealthSubQIndices((cityName2))).toMutableMap()
      }
      "Cost of living index" -> {
        city1.qIndices = (city1.qIndices + ImmIParser.getGroceriesSubQIndices((cityName1))).toMutableMap()
        city2.qIndices = (city2.qIndices + ImmIParser.getGroceriesSubQIndices((cityName2))).toMutableMap()
      }
      "Pollution index" -> {
        city1.qIndices = (city1.qIndices + ImmIParser.getPollutionSubQIndices((cityName1))).toMutableMap()
        city2.qIndices = (city2.qIndices + ImmIParser.getPollutionSubQIndices((cityName2))).toMutableMap()
      }
      "Crime index" -> {
        city1.qIndices = (city1.qIndices + ImmIParser.getCrimeSubQIndices((cityName1))).toMutableMap()
        city2.qIndices = (city2.qIndices + ImmIParser.getCrimeSubQIndices((cityName2))).toMutableMap()
      }
      "Traffic time index" -> {
        city1.qIndices = (city1.qIndices + ImmIParser.getTrafficSubQIndices((cityName1))).toMutableMap()
        city2.qIndices = (city2.qIndices + ImmIParser.getTrafficSubQIndices((cityName2))).toMutableMap()
      }

    }//each city has qindices map<String, Double>, it gets updated in required category by pressing "compare" button in CompareCities
    //and there is a map in ImmiDatabase which maps SubQindices to their corresponding categories(e.g. "skill and competency of staff" to "Healthcare");


    val mgr = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    mgr.hideSoftInputFromWindow(inputCity2.windowToken, 0)
  }
}
