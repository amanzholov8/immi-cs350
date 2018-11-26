package com.immi.thebrogrammers.immi

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_compare_cities.*


class CompareCities : AppCompatActivity() {
  lateinit var cityName1: String
  lateinit var cityName2: String
  lateinit var qindexString: String
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

  fun getCity1(): String {
    return cityName1
  }

  fun getCity2(): String {
    return cityName2
  }

  fun getCategory(): String {
    var ans: String
    ans = when (qindexString) {
      "Healthcare index" -> "Healthcare"
      "Cost of living index" -> "Cost of living"
      "Pollution index" -> "Pollution"
      "Crime index" -> "Crime"
      "Traffic time index" -> "Traffic"
      else -> "Healthcare"
    }
    return ans
  }

  fun compareTwoCities(view: View) {
    val cityInput1: EditText = findViewById<EditText>(R.id.inputCity1)
    val cityInput2: EditText = findViewById<EditText>(R.id.inputCity2)

    cityName1 = cityInput1.text.toString()
    cityName2 = cityInput2.text.toString()

    val spinnerText = indexSpinner.selectedItem.toString()
    val indMap = mapOf(
      "Healthcare quality" to "Healthcare index",
      "Cost of groceries" to "Cost of living index",
      "Pollution level" to "Pollution index",
      "Crime level" to "Crime index",
      "Traffic" to "Traffic time index")
    qindexString = indMap[spinnerText]!!
    val city1 = ImmIDatabase.getCityByName(cityName1)!!
    val city2 = ImmIDatabase.getCityByName(cityName2)!!
    if (city1.qIndices == null)
      city1.qIndices = ImmIParser.getQIndices((cityName1))

    if (city2.qIndices == null)
      city2.qIndices = ImmIParser.getQIndices((cityName2))

    val f: Fragment

    when (qindexString) {
      "Healthcare index" -> {
        city1.qIndices = (city1.qIndices + ImmIParser.getHealthSubQIndices((cityName1))).toMutableMap()
        city2.qIndices = (city2.qIndices + ImmIParser.getHealthSubQIndices((cityName2))).toMutableMap()
        f = HealthCompare()
      }
      "Cost of living index" -> {
        city1.qIndices = (city1.qIndices + ImmIParser.getGroceriesSubQIndices((cityName1))).toMutableMap()
        city2.qIndices = (city2.qIndices + ImmIParser.getGroceriesSubQIndices((cityName2))).toMutableMap()
        f = HealthCompare()
      }
      "Pollution index" -> {
        city1.qIndices = (city1.qIndices + ImmIParser.getPollutionSubQIndices((cityName1))).toMutableMap()
        city2.qIndices = (city2.qIndices + ImmIParser.getPollutionSubQIndices((cityName2))).toMutableMap()
        f = HealthCompare()
      }
      "Crime index" -> {
        city1.qIndices = (city1.qIndices + ImmIParser.getCrimeSubQIndices((cityName1))).toMutableMap()
        city2.qIndices = (city2.qIndices + ImmIParser.getCrimeSubQIndices((cityName2))).toMutableMap()
        f = HealthCompare()
      }
      "Traffic time index" -> {
        city1.qIndices = (city1.qIndices + ImmIParser.getTrafficSubQIndices((cityName1))).toMutableMap()
        city2.qIndices = (city2.qIndices + ImmIParser.getTrafficSubQIndices((cityName2))).toMutableMap()
        f = HealthCompare()
      }
      else -> {
        f = HealthCompare()
      }
    }
    //each city has qindices map<String, Double>, it gets updated in required category by pressing "compare" button in CompareCities
    //and there is a map in ImmiDatabase which maps SubQindices to their corresponding categories(e.g. "skill and competency of staff" to "Healthcare");
    if (!loadFragment(f)) {
      Toast.makeText(
        this,
        "Something went wrong with service :(",
        Toast.LENGTH_SHORT
      ).show()
    }
    val mgr = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    mgr.hideSoftInputFromWindow(inputCity2.windowToken, 0)
  }

  private fun loadFragment(fragment: Fragment?): Boolean {
    if (fragment != null) {
      supportFragmentManager.beginTransaction()
        .replace(R.id.fragment_compare_containter, fragment)
        .commit()
      return true
    }
    return false
  }
}
