package com.immi.thebrogrammers.immi

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View

class InfoShowActivity : AppCompatActivity() {

  lateinit var toolbar: Toolbar
  lateinit var drawerLayout: DrawerLayout
  lateinit var navigationView: NavigationView
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.navigation_drawer)

    toolbar = findViewById<Toolbar>(R.id.toolbar)
    setSupportActionBar(toolbar)

    drawerLayout = findViewById(R.id.drawer_layout)
    navigationView = findViewById(R.id.navigation_view)

    var toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer)
    drawerLayout.setDrawerListener(toggle)

    toggle.syncState()
  }

  fun nearbyCities(view: View) {

    // Create an Intent to start the GeoLocaitonActivity activity
    val geoLocationIntent = Intent(this, GeoLocationActivity::class.java)

    //start the new activity
    startActivity(geoLocationIntent)

  }
}
