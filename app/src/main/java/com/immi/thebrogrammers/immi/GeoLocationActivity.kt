package com.immi.thebrogrammers.immi

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_geo_location.*

class GeoLocationActivity : AppCompatActivity() {
  lateinit var scroll: ListView


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_geo_location)

    scroll = findViewById<ListView>(R.id.scroll)

    val myCityName = intent.getStringExtra("GEO_OBJECT_NAME")!!
    val myCity = ImmIDatabase.getCityByName(myCityName)

    textView_message.text = getString(R.string.geoLocationHeading, myCityName)

    val nearbyCities = ImmIDatabase.searchNearbyCity(myCity!!)
    val nearbyCitiesNames = nearbyCities.map({ c -> c.geo_name })

    scroll.adapter = ArrayAdapter<String>(
      this,
      R.layout.nearby_cities_listview,
      nearbyCitiesNames)

    scroll.setOnItemClickListener { adapterView: AdapterView<*>?, view: View?, i: Int, l: Long ->
      val infoShowIntent = Intent(this, InfoShowActivity::class.java)
      infoShowIntent.putExtra("GEO_OBJECT_NAME", nearbyCitiesNames[i])
      startActivity(infoShowIntent)
    }
  }
}
