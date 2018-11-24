package com.immi.thebrogrammers.immi

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_info_show.*

@Suppress("UNREACHABLE_CODE")
class InfoShowActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


  lateinit var toolbar: Toolbar
  lateinit var drawerLayout: DrawerLayout
  lateinit var navigationView: NavigationView
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.navigation_drawer)
    var geo_name: String
    if (intent.hasExtra("GEO_OBJECT_NAME")) {
      cityText.text = intent.getStringExtra("GEO_OBJECT_NAME")!!
      val curCity = (ImmIDatabase.getCityByName(cityText.text.toString()))
      countryText.text = curCity?.country
      //countryText.setText(curCity?.currency)
    }

    val geoName = intent.getStringExtra("GEO_OBJECT_NAME")!!
    Log.d("debug_info", geoName)
    //textview_title.text = geoName


    toolbar = findViewById<Toolbar>(R.id.toolbar)
    setSupportActionBar(toolbar)

    drawerLayout = findViewById(R.id.drawer_layout)
    navigationView = findViewById(R.id.navigation_view)

    val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer)
    drawerLayout.setDrawerListener(toggle)
    navigationView.setNavigationItemSelectedListener(this)

    toggle.syncState()
  }


  override fun onNavigationItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.home_id -> {
        val homeIntent = Intent(this, MainActivity::class.java)

        startActivity(homeIntent)
      }

      R.id.compare_id -> {
        val compareCitiesIntent = Intent(this, CompareCities::class.java)
        val geoName = intent.getStringExtra("GEO_OBJECT_NAME")!!

        compareCitiesIntent.putExtra("GEO_OBJECT_NAME", geoName)

        startActivity(compareCitiesIntent)
      }
      R.id.converter_id -> {
        val converterIntent = Intent(this, CurrencyConverter::class.java)

        startActivity(converterIntent)
      }
      R.id.nearby_id -> {
        // Create an Intent to start the GeoLocaitonActivity activity
        val geoLocationIntent = Intent(this, GeoLocationActivity::class.java)
        val geoName = intent.getStringExtra("GEO_OBJECT_NAME")!!

        geoLocationIntent.putExtra("GEO_OBJECT_NAME", geoName)

        //start the new activity
        startActivity(geoLocationIntent)
      }
      R.id.location_id -> {
        val homeIntent = Intent(this, MainActivity::class.java)

        startActivity(homeIntent)
      }
    }
    return true
  }
}