package com.immi.thebrogrammers.immi

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_info_show.*

@Suppress("UNREACHABLE_CODE")
class InfoShowActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
  lateinit var toolbar: Toolbar
  lateinit var drawerLayout: DrawerLayout
  lateinit var navigationView: NavigationView
  lateinit var bottomNavView: BottomNavigationView


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
    navigationView.setNavigationItemSelectedListener(this)

    toggle.syncState()

    bottomNavView = findViewById(R.id.bottom_navigation)

    val qindex = ImmIDatabase.qindices

    loadFragment(CostFragment())

    bottomNavView.setOnNavigationItemSelectedListener(object : BottomNavigationView.OnNavigationItemSelectedListener {
      override fun onNavigationItemSelected(item: MenuItem): Boolean {
        lateinit var fragment: Fragment
        when (item.itemId) {
          R.id.action_money -> {
            fragment = CostFragment()
          }

          R.id.action_crime -> {
            fragment = CrimeFragment()
          }

          R.id.action_eco -> {
            fragment = PollutionFragment()
          }

          R.id.action_traffic -> {
            fragment = TrafficFragment()
          }

          R.id.action_med -> {
            fragment = HealthFragment()
          }
        }
        return loadFragment(fragment)
      }
    })
  }
  
  fun loadFragment(fragment: Fragment): Boolean {
    if (fragment != null) {
      supportFragmentManager.beginTransaction()
        .replace(R.id.fragment_container, fragment)
        .commit()
      return true
    }
    return false
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
        val geoLocationIntent = Intent(this, GeoLocationActivity::class.java)
        val geoName = intent.getStringExtra("GEO_OBJECT_NAME")!!
        geoLocationIntent.putExtra("GEO_OBJECT_NAME", geoName)
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