package com.immi.thebrogrammers.immi

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
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
      "Healthcare quality" to "health_care_index",
      "Cost of groceries" to "groceries_index",
      "Pollution level" to "pollution_index",
      "Crime level" to "crime_index",
      "Traffic" to "traffic_index")
    val qindexString = indMap[spinnerText]
    val city1 = ImmIDatabase.getCityByName(cityName1)
    val city2 = ImmIDatabase.getCityByName(cityName2)
    city1?.qIndices = ImmIParser.getQIndices(cityName1)
    city2?.qIndices = ImmIParser.getQIndices(cityName2)
    val qindex = ImmIDatabase.getQIndexByName(qindexString!!)
    val ans = city1?.compareCities(city2!!, qindex!!)
    //answerText.text = ans
    val f: Fragment
    when (qindexString) {
      "health_care_index" -> {
        f = HealthCompare()
      }
      else -> {
        f = HealthCompare()
      }
    }
    if (!loadFragment(f)) {
      Toast.makeText(
        this,
        "Something went wrong with service :(",
        Toast.LENGTH_SHORT
      ).show()
    }
    //val mgr = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    //mgr.hideSoftInputFromWindow(inputCity2.windowToken, 0)
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
