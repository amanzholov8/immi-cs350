package com.immi.thebrogrammers.immi

import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
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
    val map = mutableMapOf(
      "Crime index" to homefeed.crime_index,
      "Traffic time index" to homefeed.traffic_time_index,
      "Healthcare index" to homefeed.health_care_index,
      "Pollution index" to homefeed.pollution_index,
      "Cost of living index" to homefeed.groceries_index)
    ImmIDatabase.categoryMap["Traffic time index"] = "Traffic"
    ImmIDatabase.categoryMap["Healthcare index"] = "Healthcare"
    ImmIDatabase.categoryMap["Crime index"] = "Crime"
    ImmIDatabase.categoryMap["Pollution index"] = "Pollution"
    ImmIDatabase.categoryMap["Cost of living index"] = "Cost of living"
    return map
  }

  fun getHealthSubQIndices(city_name: String): MutableMap<String, Double> {
    val url = buildURL(city_health, city_name)
    val body = ImmIParser.makeReq(url)
    val gson = GsonBuilder().create()
    val homefeed = gson.fromJson(body, HealthQIndices::class.java)
    val map = mutableMapOf(
      "Skill and competency of medical staff" to normalization(homefeed.skill_and_competency),
      "Speed in completing examination and reports" to normalization(homefeed.speed),
      "Equipment for modern diagnosis and treatment" to normalization(homefeed.modern_equipment),
      "Accuracy and completeness in filling out reports" to normalization(homefeed.accuracy_and_completeness),
      "Friendliness and courtesy of the staff" to normalization(homefeed.friendliness_and_courtesy),
      "Satisfaction with responsiveness (waitings) in medical institutions" to normalization(homefeed.responsiveness_waitings),
      "Satisfaction with cost to you" to normalization(homefeed.cost),
      "Convenience of location for you" to normalization(homefeed.location))
    ImmIDatabase.categoryMap["Speed in completing examination and repots"] = "Healthcare"
    ImmIDatabase.categoryMap["Equipment for modern diagnosis and treatment"] = "Healthcare"
    ImmIDatabase.categoryMap["Accuracy and completeness in filling out reports"] = "Healthcare"
    ImmIDatabase.categoryMap["Friendliness and courtesy of the staff"] = "Healthcare"
    ImmIDatabase.categoryMap["Satisfaction with responsiveness (waitings) in medical institutions"] = "Healthcare"
    ImmIDatabase.categoryMap["Satisfaction with cost to you"] = "Healthcare"
    ImmIDatabase.categoryMap["Convenience of location for you"] = "Healthcare"
    return map
  }

  fun getCrimeSubQIndices(city_name: String): MutableMap<String, Double> {
    val url = buildURL(city_crime, city_name)
    val body = ImmIParser.makeReq(url)
    val gson = GsonBuilder().create()
    val homefeed = gson.fromJson(body, CrimeQIndices::class.java)
    val map = mutableMapOf(
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
    ImmIDatabase.categoryMap["Level of crime"] = "Crime"
    ImmIDatabase.categoryMap["Crime increasing in the past 3 years"] = "Crime"
    ImmIDatabase.categoryMap["Worries home broken and things stolen"] = "Crime"
    ImmIDatabase.categoryMap["Worries being mugged or robbed"] = "Crime"
    ImmIDatabase.categoryMap["Worries car stolen"] = "Crime"
    ImmIDatabase.categoryMap["Worries things from car stolen"] = "Crime"
    ImmIDatabase.categoryMap["Worries attacked"] = "Crime"
    ImmIDatabase.categoryMap["Worries being insulted"] = "Crime"
    ImmIDatabase.categoryMap["Worries being subject to a physical attack because of your skin colour, ethnic origin or religion"] = "Crime"
    ImmIDatabase.categoryMap["Problem people using or dealing drugs"] = "Crime"
    ImmIDatabase.categoryMap["Problem property crimes such as vandalism and theft"] = "Crime"
    ImmIDatabase.categoryMap["Problem violent crimes such as assault and armed robbery"] = "Crime"
    ImmIDatabase.categoryMap["Problem corruption and bribery"] = "Crime"
    ImmIDatabase.categoryMap["Safety walking alone during daylight"] = "Crime"
    ImmIDatabase.categoryMap["Safety walking alone during night"] = "Crime"
    return map
  }

  fun getPollutionSubQIndices(city_name: String): MutableMap<String, Double> {
    val url = buildURL(city_pollution, city_name)
    val body = ImmIParser.makeReq(url)
    val gson = GsonBuilder().create()
    val homefeed = gson.fromJson(body, PollutionQIndices::class.java)
    val map = mutableMapOf(
      "Air quality" to normalization(homefeed.air_quality),
      "Drinking water quality and accessibility" to normalization(homefeed.drinking_water_quality_accessibility),
      "Garbage disposal satisfaction" to normalization(homefeed.garbage_disposal_satisfaction),
      "Clean and tidy" to normalization(homefeed.clean_and_tidy),
      "Water pollution" to normalization(homefeed.water_pollution),
      "Noise and light pollution" to normalization(homefeed.noise_and_light_pollution),
      "Comfortable to spend time" to normalization(homefeed.comfortable_to_spend_time),
      "Quality of green parks" to normalization(homefeed.green_parks_quality))
    ImmIDatabase.categoryMap["Drinking water quality and accessibility"] = "Pollution"
    ImmIDatabase.categoryMap["Garbage disposal satisfaction"] = "Pollution"
    ImmIDatabase.categoryMap["Clean and tidy"] = "Pollution"
    ImmIDatabase.categoryMap["Water pollution"] = "Pollution"
    ImmIDatabase.categoryMap["Noise and light pollution"] = "Pollution"
    ImmIDatabase.categoryMap["Comfortable to spend time"] = "Pollution"
    ImmIDatabase.categoryMap["Quality of green parks"] = "Pollution"

    return map
  }

  fun getTrafficSubQIndices(city_name: String): MutableMap<String, Double> {
    val url = buildURL(city_traffic, city_name)
    val body = ImmIParser.makeReq(url)
    val gson = GsonBuilder().create()
    val homefeed = gson.fromJson(body, TrafficQIndices::class.java)
    val map = mutableMapOf(
      "Time index" to (homefeed.index_time),
      "Time Exp. Index" to (homefeed.index_time_exp),
      "CO2 Emission Index" to (homefeed.index_co2_emission),
      "Working from home percentage" to (homefeed.primary_means_percentage_map.Working_from_home),
      "Car usage percentage" to (homefeed.primary_means_percentage_map.Car),
      "Bike usage percentage" to (homefeed.primary_means_percentage_map.Bike),
      "Motorbike usage percentage" to (homefeed.primary_means_percentage_map.Motorbike),
      "Bus/Trolleybus usage percentage" to (homefeed.primary_means_percentage_map.Bus),
      "Train/Metro usage percentage" to (homefeed.primary_means_percentage_map.Train),
      "Walking percentage" to (homefeed.primary_means_percentage_map.Walking))
    ImmIDatabase.categoryMap["Time Exp. Index"] = "Traffic"
    ImmIDatabase.categoryMap["CO2 Emission Index"] = "Traffic"
    ImmIDatabase.categoryMap["Working from home percentage"] = "Traffic"
    ImmIDatabase.categoryMap["Car usage percentage"] = "Traffic"
    ImmIDatabase.categoryMap["Bike usage percentage"] = "Traffic"
    ImmIDatabase.categoryMap["Motorbike usage percentage"] = "Traffic"
    ImmIDatabase.categoryMap["Bus/Trolleybus usage percentage"] = "Traffic"
    ImmIDatabase.categoryMap["Train/Metro usage percentage"] = "Traffic"
    ImmIDatabase.categoryMap["Walking percentage"] = "Traffic"

    return map
  }

  fun getGroceriesSubQIndices(city_name: String): MutableMap<String, Double> {
    val url = buildURL(city_price, city_name)
    val body = ImmIParser.makeReq(url)
    val gson = GsonBuilder().create()
    val homefeed = gson.fromJson(body, GroceriesIndices::class.java)
    val map: MutableMap<String, Double> = mutableMapOf()
    for (i in homefeed.prices) {
      map[i.item_name] = i.average_price
      ImmIDatabase.categoryMap[i.item_name] = "Cost of living"
    }
    return map
  }

  fun buildURL(category_of_index: String, city_name: String): String {
    val head = "https://www.numbeo.com/api/"
    val api = "?api_key=1yj7w26vkf03z6"
    val query = "&query="
    return head + category_of_index + api + query + city_name
  }

  fun normalization(value: Double): Double {
    return (value + 2) * 25
  }
}

class GrocerItem(
  val item_name: String,
  val average_price: Double)

class GroceriesIndices(
  val prices: Array<GrocerItem>
)

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


class PollutionQIndices(
  val drinking_water_quality_accessibility: Double,
  val noise_and_light_pollution: Double,
  val water_pollution: Double,
  val garbage_disposal_satisfaction: Double,
  val clean_and_tidy: Double,
  val air_quality: Double,
  val comfortable_to_spend_time: Double,
  val green_parks_quality: Double)


class CrimeQIndices(
  val worried_attacked: Double,
  val problem_property_crimes: Double,
  val safe_alone_night: Double,
  val worried_skin_ethnic_religion: Double,
  val worried_car_stolen: Double,
  val worried_home_broken: Double,
  val worried_things_car_stolen: Double,
  val crime_increasing: Double,
  val problem_corruption_bribery: Double,
  val safe_alone_daylight: Double,
  val problem_drugs: Double,
  val worried_insulted: Double,
  val problem_violent_crimes: Double,
  val worried_mugged_robbed: Double,
  val level_of_crime: Double)

class TrafficQIndices(
  val index_co2_emission: Double,
  val index_time: Double,
  val index_time_exp: Double,
  val primary_means_percentage_map: primary_means_percentage
)

class primary_means_percentage(
  @SerializedName("Working from home") val Working_from_home: Double,
  @SerializedName("Tram/Streetcar") val tram_streetcar: Double,
  @SerializedName("Train/Metro") val Train: Double,
  @SerializedName("Bus/Trolleybus") val Bus: Double,
  val Car: Double,
  val Bike: Double,
  val Walking: Double,
  val Motorbike: Double)

class CitiesFeed(val cities: List<City>)
class CurrFeed(val exchange_rates: List<Currency>)
