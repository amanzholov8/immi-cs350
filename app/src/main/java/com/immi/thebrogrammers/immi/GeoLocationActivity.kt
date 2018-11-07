package com.immi.thebrogrammers.immi

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_geo_location.*

class GeoLocationActivity : AppCompatActivity() {
  val db = ImmIDatabase()
  lateinit var scroll: ListView


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_geo_location)

    scroll = findViewById<ListView>(R.id.scroll)

    val myCityName = "Daejeon"
    val myCity = db.getCityByName(myCityName)
    //val myCity = db.cites[-1]

    textView_message.text = getString(R.string.geoLocationHeading, myCityName)

    val nearbyCities = db.searchNearbyCity(myCity!!)
    val nearbyCitiesNames = nearbyCities.map({ c -> c.geo_name })

    scroll.adapter = ArrayAdapter<String>(
      this,
      R.layout.nearby_cities_listview,
      nearbyCitiesNames)
  }
}
