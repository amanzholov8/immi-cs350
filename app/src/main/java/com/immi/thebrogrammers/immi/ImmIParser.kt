package com.immi.thebrogrammers.immi

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

object ImmIParser {
  var client = OkHttpClient()
  val curr_host = "https://www.numbeo.com/api/currency_exchange_rates?api_key=1yj7w26vkf03z6"
  val cities_host = "https://www.numbeo.com/api/cities?api_key=1yj7w26vkf03z6"
  val indices_host = "https://www.numbeo.com/api/indices?api_key=1yj7w26vkf03z6&query="
  val city_health = "city_healthcare"
  val city_crime = "city_crime"
  val city_pollution = "city_pollution"
  val city_price = "city_prices"
  val city_traffic = "city_traffic"

  @Throws(IOException::class)
  fun run(url: String): String {
    val request = Request.Builder()
      .url(url)
      .build()

    client.newCall(request).execute().use { response -> return response.body()!!.string() }
  }

  fun makeReq(url: String): String {
    var ret = ""
    val thread = Thread(Runnable {
      try {
        //Your code goes here
        ret = ImmIParser.run(url)
        println(ret)
      } catch (e: Exception) {
        e.printStackTrace()
      }
    })
    thread.start()
    thread.join()
    return ret
  }

  fun getCities(): CitiesFeed {
    val body = ImmIParser.makeReq(cities_host)
    val gson = GsonBuilder().create()
    val homefeed = gson.fromJson(body, CitiesFeed::class.java)
    return homefeed
  }

  fun getCurrencies(): CurrFeed {
    val body = ImmIParser.makeReq(curr_host)
    val gson = GsonBuilder().create()
    val homefeed = gson.fromJson(body, CurrFeed::class.java)
    return homefeed
  }

  fun getQIndices(name: String): MutableMap<String, Double> {
    val url = indices_host + name
    val body = ImmIParser.makeReq(url)
    val gson = GsonBuilder().create()
    val homefeed = gson.fromJson(body, QIndices::class.java)
    val map = mutableMapOf("crime_index" to homefeed.crime_index,
      "traffic_time_index" to homefeed.traffic_time_index,
      "health_care_index" to homefeed.health_care_index,
      "pollution_index" to homefeed.pollution_index,
      "groceries_index" to homefeed.groceries_index)
    return map
  }

  fun getHealthSubQIndeces(city_name: String): MutableMap<String, Double> {
    val url = buildURL(city_health, city_name)
    val body = ImmIParser.makeReq(url)
    val gson = GsonBuilder().create()
    val homefeed = gson.fromJson(body, HealthQIndices::class.java)
    val map = mutableMapOf("Skill and competency of medical staff" to homefeed.skill_and_competency,
      "Speed in completing examination and reports" to homefeed.speed,
      "Equipment for modern diagnosis and treatment" to homefeed.modern_equipment,
      "Accuracy and completeness in filling out reports" to homefeed.accuracy_and_completeness,
      "Friendliness and courtesy of the staff" to homefeed.friendliness_and_courtesy,
      "Satisfaction with responsiveness (waitings) in medical institutions" to homefeed.responsiveness_waitings,
      "Satisfaction with cost to you" to homefeed.cost,
      "Convenience of location for you" to homefeed.location)
    return map
  }

  fun buildURL(category_of_index: String, city_name: String): String {
    val head = "https://www.numbeo.com/api/"
    val api = "?api_key=1yj7w26vkf03z6"
    val query = "&query="
    return head + category_of_index + api + query + city_name;
  }
}

class QIndices(
  val crime_index: Double,
  val traffic_time_index: Double,
  val health_care_index: Double,
  val pollution_index: Double,
  val groceries_index: Double)

class HealthQIndices(
  val skill_and_competency: Double,
  val speed: Double,
  val cost: Double,
  val responsiveness_waitings: Double,
  val accuracy_and_completeness: Double,
  val friendliness_and_courtesy: Double,
  val modern_equipment: Double,
  val location: Double)

class CitiesFeed(val cities: List<City>)
class CurrFeed(val exchange_rates: List<Currency>)
