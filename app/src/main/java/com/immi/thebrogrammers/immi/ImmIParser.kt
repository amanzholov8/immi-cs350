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

  fun getHealthSubQIndices(city_name: String): MutableMap<String, Int> {
    val url = buildURL(city_health, city_name)
    val body = ImmIParser.makeReq(url)
    val gson = GsonBuilder().create()
    val homefeed = gson.fromJson(body, HealthQIndices::class.java)
    val map = mutableMapOf("Skill and competency of medical staff" to normalization(homefeed.skill_and_competency),
      "Speed in completing examination and reports" to normalization(homefeed.speed),
      "Equipment for modern diagnosis and treatment" to normalization(homefeed.modern_equipment),
      "Accuracy and completeness in filling out reports" to normalization(homefeed.accuracy_and_completeness),
      "Friendliness and courtesy of the staff" to normalization(homefeed.friendliness_and_courtesy),
      "Satisfaction with responsiveness (waitings) in medical institutions" to normalization(homefeed.responsiveness_waitings),
      "Satisfaction with cost to you" to normalization(homefeed.cost),
      "Convenience of location for you" to normalization(homefeed.location))
    return map
  }

  fun getCrimeSubQIndices(city_name: String): MutableMap<String, Int> {
    val url = buildURL(city_crime, city_name)
    val body = ImmIParser.makeReq(url)
    val gson = GsonBuilder().create()
    val homefeed = gson.fromJson(body, CrimeQIndices::class.java)
    val map = mutableMapOf("\n" +
      "Level of crime" to normalization(homefeed.level_of_crime),
      "Crime increasing in the past 3 years" to normalization(homefeed.crime_increasing),
      "Worries home broken and things stolen" to normalization(homefeed.worried_home_broken),
      "Worries being mugged or robbed" to normalization(homefeed.worried_mugged_robbed),
      "Worries car stolen" to normalization(homefeed.worried_car_stolen),
      "Worries things from car stolen" to normalization(homefeed.worried_things_car_stolen),
      "Worries attacked" to normalization(homefeed.worried_attacked),
      "Worries being insulted" to normalization(homefeed.worried_insulted),
      "Worries being subject to a physical attack because of your skin colour, ethnic origin or religion" to normalization(homefeed.worried_skin_ethnic_religion),
      "Problem people using or dealing drugs" to normalization(homefeed.problem_drugs),
      "Problem property crimes such as vandalism and theft" to normalization(homefeed.problem_property_crimes),
      "Problem violent crimes such as assault and armed robbery" to normalization(homefeed.problem_violent_crimes),
      "Problem corruption and bribery" to normalization(homefeed.problem_corruption_bribery),
      "Safety walking alone during daylight" to normalization(homefeed.safe_alone_daylight),
      "Safety walking alone during night" to normalization(homefeed.safe_alone_night))
    return map
  }

  fun buildURL(category_of_index: String, city_name: String): String {
    val head = "https://www.numbeo.com/api/"
    val api = "?api_key=1yj7w26vkf03z6"
    val query = "&query="
    return head + category_of_index + api + query + city_name;
  }

  fun normalization(value: Int): Int {
    return (value + 2) * 25
  }
}

class QIndices(
  val crime_index: Double,
  val traffic_time_index: Double,
  val health_care_index: Double,
  val pollution_index: Double,
  val groceries_index: Double)

class HealthQIndices(
  val skill_and_competency: Int,
  val speed: Int,
  val cost: Int,
  val responsiveness_waitings: Int,
  val accuracy_and_completeness: Int,
  val friendliness_and_courtesy: Int,
  val modern_equipment: Int,
  val location: Int)

class CrimeQIndices(
  val worried_attacked: Int,
  val problem_property_crimes: Int,
  val safe_alone_night: Int,
  val worried_skin_ethnic_religion: Int,
  val worried_car_stolen: Int,
  val worried_home_broken: Int,
  val worried_things_car_stolen: Int,
  val crime_increasing: Int,
  val problem_corruption_bribery: Int,
  val safe_alone_daylight: Int,
  val problem_drugs: Int,
  val worried_insulted: Int,
  val problem_violent_crimes: Int,
  val worried_mugged_robbed: Int,
  val level_of_crime: Int)

class CitiesFeed(val cities: List<City>)
class CurrFeed(val exchange_rates: List<Currency>)
