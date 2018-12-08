package com.immi.thebrogrammers.immi

import android.location.Location
import android.util.Log

object ImmIDatabase {

  val cities = ImmIParser.getCities().cities
  val countries = arrayListOf<String>()
  val currencies = ImmIParser.getCurrencies().exchange_rates
  val curr_map = mutableMapOf<String, Currency>()
  val qindices = arrayListOf<QIndex>()
  val categoryMap: MutableMap<String, String> = mutableMapOf()
  init {
    //NOTE: Init the arrays with some dummy data


    //Qindices initialization
    qindices.add(
      QIndex(
        qname = "health_care_index",
        descr = "Healthcare system's quality ",
        seps = arrayOf(55.0, 70.0),
        quals = arrayOf("bad", "moderate", "good")
      )
    )
    qindices.add(
      QIndex(
        qname = "groceries_index",
        descr = "Cost of groceries ",
        seps = arrayOf(55.0, 90.0),
        quals = arrayOf("cheap", "moderate", "expensive")
      )
    )
    qindices.add(
      QIndex(
        qname = "pollution_index",
        descr = "Polution level ",
        seps = arrayOf(42.0, 75.0),
        quals = arrayOf("low", "moderate", "high")
      )
    )
    qindices.add(
      QIndex(
        qname = "crime_index",
        descr = "Crime level ",
        seps = arrayOf(35.0, 60.0),
        quals = arrayOf("low", "moderate", "high")
      )
    )
    qindices.add(
      QIndex(
        qname = "traffic_index",
        descr = "Average time spent on traffic ",
        seps = arrayOf(150.0, 225.0),
        quals = arrayOf("low", "moderate", "high")
      )
    )


    qindices.add(
      QIndex(
        qname = "health_care_index",
        descr = "Healthcare system's quality ",
        seps = arrayOf(55.0, 70.0),
        quals = arrayOf("bad", "moderate", "good")
      )
    )

    qindices.add(
      QIndex(
        qname = "groceries_index",
        descr = "Cost of groceries ",
        seps = arrayOf(55.0, 90.0),
        quals = arrayOf("cheap", "moderate", "expensive")
      )
    )

    qindices.add(
      QIndex(
        qname = "pollution_index",
        descr = "Polution level ",
        seps = arrayOf(42.0, 75.0),
        quals = arrayOf("low", "moderate", "high")
      )
    )

    qindices.add(
      QIndex(
        qname = "crime_index",
        descr = "Crime level ",
        seps = arrayOf(35.0, 60.0),
        quals = arrayOf("low", "moderate", "high")
      )
    )

    qindices.add(
      QIndex(
        qname = "traffic_index",
        descr = "Average time spent on traffic ",
        seps = arrayOf(150.0, 225.0),
        quals = arrayOf("low", "moderate", "high")
      )
    )

    for (cur in currencies) {
      curr_map.put(cur.cur_name, cur)
    }
  }

  fun findCurrencyByCode(curr_code: String): Currency? {
    if (curr_map.containsKey(curr_code)) {
      return curr_map[curr_code]
    } else {
      throw Exception("Currency code ${curr_code} doesn't exist")
    }
  }


  fun convertCurrencies(from: String, to: String, amount: Double): Double {
    val fromCurr = findCurrencyByCode(from)!!
    val amountToUsd = amount / fromCurr.usd_to_cur
    val toCurr = findCurrencyByCode(to)!!
    val result = toCurr.usd_to_cur * amountToUsd
    return result
  }


  fun getQIndexByName(qname: String): QIndex? {
    for (qindex in qindices) {
      if (qindex.qname == qname)
        return qindex
    }
    return null
  }

  fun getCityByName(cityName: String): City? {
    for (city in cities) {
      if (city.geo_name == cityName)
        return city
    }
    return null
  }


  fun searchNearbyCity(city: City, thresholdDist: Double = 350.0): ArrayList<City> {
    val nearbyCities = arrayListOf<City>()

    for (each_city in cities) {
      if (each_city.geo_name != city.geo_name &&
        getDist(city.lat, city.lon, each_city.lat, each_city.lon) < thresholdDist) {
        nearbyCities.add(each_city)
      }
    }
    return nearbyCities
  }

  fun getDist(lat1: Float, lon1: Float, lat2: Float, lon2: Float): Double {
    val R = 6371 // Radius of the earth in km
    val dLat = deg2rad(lat2 - lat1)  // deg2rad below
    val dLon = deg2rad(lon2 - lon1)
    val a =
      Math.sin(dLat / 2) * Math.sin(dLat / 2) +
        Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
        Math.sin(dLon / 2) * Math.sin(dLon / 2)
    val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
    val d = R * c // Distance in km
    return d
  }

  fun deg2rad(deg: Float): Double {
    return deg * (Math.PI / 180)
  }

  // Check this function later
  fun getDistance(lat1: Float, lon1: Float, lat2: Float, lon2: Float): Float {

    val loc1 = Location("")
    loc1.latitude = lat1.toDouble()
    loc1.longitude = lon1.toDouble()
    val loc2 = Location("")
    loc2.latitude = lat2.toDouble()
    loc2.longitude = lon2.toDouble()

    val distanceInMeters = loc1.distanceTo(loc2)
    Log.d("getDistance", "Distance: $distanceInMeters")
    return distanceInMeters
  }

}