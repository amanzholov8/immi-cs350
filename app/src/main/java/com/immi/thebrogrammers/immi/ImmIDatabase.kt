package com.immi.thebrogrammers.immi

import android.location.Location
import android.util.Log

class ImmIDatabase {

  val cities = arrayListOf<City>()
  val countries = arrayListOf<Country>()
  val currencies = arrayListOf<Currency>()
  val curr_map = mutableMapOf<String, Currency>()
  val qindices = arrayListOf<QIndex>()

  init {
    //NOTE: Init the arrays with some dummy data

    //Currencies' initialization
    currencies.add(
      Currency(
        cur_name = "USD",
        usd_to_cur = 1.0,
        eur_to_cur = 1.14
      ))
    currencies.add(
      Currency(
        cur_name = "AED",
        usd_to_cur = 3.6731809999999996,
        eur_to_cur = 4.190760928470784
      ))
    currencies.add(
      Currency(
        cur_name = "KZT",
        usd_to_cur = 370.490964,
        eur_to_cur = 422.69603819759385
      ))
    currencies.add(
      Currency(
        cur_name = "RUB",
        usd_to_cur = 65.7162,
        eur_to_cur = 74.97612650385912
      ))
    currencies.add(
      Currency(
        cur_name = "KRW",
        usd_to_cur = 1123.83,
        eur_to_cur = 1282.1864357469237
      ))
    currencies.add(
      Currency(
        cur_name = "AUD",
        usd_to_cur = 1.381104,
        eur_to_cur = 1.5757123543203329
      ))
    currencies.add(
      Currency(
        cur_name = "CAD",
        usd_to_cur = 1.307928,
        eur_to_cur = 1.4922252836582068
      ))
    currencies.add(
      Currency(
        cur_name = "AZN",
        usd_to_cur = 1.7025,
        eur_to_cur = 1.9423955641503943
      ))


    //Countries initialization
    countries.add(
      Country(
        geo_name = "USA",
        lat = 0f,
        lon = 0f,
        currency = currencies[0]
      ))
    countries.add(
      Country(
        geo_name = "United Arab Emirates",
        lat = 0f,
        lon = 0f,
        currency = currencies[1]
      ))
    countries.add(
      Country(
        geo_name = "Kazakhstan",
        lat = 0f,
        lon = 0f,
        currency = currencies[2]
      ))
    countries.add(
      Country(
        geo_name = "Russia",
        lat = 0f,
        lon = 0f,
        currency = currencies[3]
      ))
    countries.add(
      Country(
        geo_name = "South Korea",
        lat = 0f,
        lon = 0f,
        currency = currencies[4]
      ))
    countries.add(
      Country(
        geo_name = "Australia",
        lat = 0f,
        lon = 0f,
        currency = currencies[5]
      ))
    countries.add(
      Country(
        geo_name = "Canada",
        lat = 0f,
        lon = 0f,
        currency = currencies[6]
      ))
    countries.add(
      Country(
        geo_name = "Azerbaijan",
        lat = 0f,
        lon = 0f,
        currency = currencies[7]
      ))

    //Cities initialization
    cities.add(
      City(
        geo_name = "Seattle",
        lat = 34.22f,
        lon = 12.1f,
        country = countries[0]
      ))
    cities.add(
      City(
        geo_name = "Dubai",
        lat = 25.2048493f,
        lon = 55.2707828f,
        country = countries[1]
      ))
    cities.add(
      City(
        geo_name = "Almaty",
        lat = 43.2220146f,
        lon = 76.8512485f,
        country = countries[2]
      ))
    cities.add(
      City(
        geo_name = "Moscow",
        lat = 55.755826f,
        lon = 37.6173f,
        country = countries[3]
      ))
    cities.add(
      City(
        geo_name = "Seoul",
        lat = 37.566535f,
        lon = 126.9779692f,
        country = countries[4]
      ))
    cities.add(
      City(
        geo_name = "Melbourne",
        lat = -37.814251f,
        lon = 144.963169f,
        country = countries[5]
      ))
    cities.add(
      City(
        geo_name = "Ottawa",
        lat = 45.4215296f,
        lon = -75.69719309999999f,
        country = countries[6],
        indices = mutableMapOf("health_care_index" to 25.0, "groceries_index" to 23.0,
          "crime_index" to 55.0, "traffic_index" to 26.0, "pollution_index" to 57.0)
      ))
    cities.add(
      City(
        geo_name = "Baku",
        lat = 40.40926169999999f,
        lon = 49.8670924f,
        country = countries[7],
        indices = mutableMapOf("health_care_index" to 21.0, "groceries_index" to 21.0,
          "crime_index" to 15.0, "traffic_index" to 16.0, "pollution_index" to 17.0)
      ))

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

    cities.add(
      City(
        geo_name = "Busan",
        lat = 35.1795543f,
        lon = 129.0756416f,
        country = countries[4]
      ))


    cities.add(
      City(
        geo_name = "Daejeon",
        lat = 36.3504119f,
        lon = 127.3845475f,
        country = countries[4]
      ))


    cities.add(
      City(
        geo_name = "Gwangju",
        lat = 35.1595454f,
        lon = 126.8526012f,
        country = countries[4]
      ))

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
    throw Exception("Index with name ${qname} doesn't exist")
  }

  fun getCityByName(cityName: String): City? {
    for (city in cities) {
      if (city.geo_name == cityName)
        return city
    }
    throw Exception("City with name ${cityName} doesn't exist")
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