package com.immi.thebrogrammers.immi

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class InfoShowActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_info_show)
  }

  fun nearbyCities(view: View) {

    // Create an Intent to start the GeoLocaitonActivity activity
    val geoLocationIntent = Intent(this, GeoLocationActivity::class.java)

    //start the new activity
    startActivity(geoLocationIntent)

  }
}
