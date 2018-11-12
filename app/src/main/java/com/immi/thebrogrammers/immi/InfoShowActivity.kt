package com.immi.thebrogrammers.immi

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_info_show.*

class InfoShowActivity : AppCompatActivity() {

  lateinit var toolbar: Toolbar

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_info_show)

    val geoName = intent.getStringExtra("GEO_OBJECT_NAME")!!
    Log.d("debug_info", geoName)
    textview_title.text = geoName


    toolbar = findViewById<Toolbar>(R.id.toolbar)
    setSupportActionBar(toolbar)
  }

  fun nearbyCities(view: View) {

    // Create an Intent to start the GeoLocaitonActivity activity
    val geoLocationIntent = Intent(this, GeoLocationActivity::class.java)

    //start the new activity
    startActivity(geoLocationIntent)

  }
}
