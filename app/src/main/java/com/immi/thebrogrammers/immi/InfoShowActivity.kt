package com.immi.thebrogrammers.immi

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.util.Log
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

    val geoName = intent.getStringExtra("GEO_OBJECT_NAME")!!
    Log.d("debug_info", geoName)
    //textview_title.text = geoName


    toolbar = findViewById<Toolbar>(R.id.toolbar)
    setSupportActionBar(toolbar)

    drawerLayout = findViewById(R.id.drawer_layout)
    navigationView = findViewById(R.id.navigation_view)

    val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer)
    drawerLayout.setDrawerListener(toggle)

    toggle.syncState()
  }

  fun nearbyCities(view: View) {

    // Create an Intent to start the GeoLocaitonActivity activity
    val geoLocationIntent = Intent(this, GeoLocationActivity::class.java)
    val geoName = intent.getStringExtra("GEO_OBJECT_NAME")!!

    geoLocationIntent.putExtra("GEO_OBJECT_NAME", geoName)

    //start the new activity
    startActivity(geoLocationIntent)

  }

  override fun onNavigationItemSelected(item: MenuItem): Boolean {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    when (item.itemId) {
      R.id.home_id -> {

      }
      R.id.compare_id -> {

      }
      R.id.converter_id -> {

      }
      R.id.nearby_id -> {

      }
      R.id.location_id -> {

      }
    }
  }

  fun compareCitiesButton(view: View) {

    val compareCitiesIntent = Intent(this, CompareCities::class.java)
    val geoName = intent.getStringExtra("GEO_OBJECT_NAME")!!

    compareCitiesIntent.putExtra("GEO_OBJECT_NAME", geoName)

    startActivity(compareCitiesIntent)

  }

  fun currencyConverterButton(view: View) {

    val currencyConverterIntent = Intent(this, CurrencyConverter::class.java)

    startActivity(currencyConverterIntent)

  }
}
