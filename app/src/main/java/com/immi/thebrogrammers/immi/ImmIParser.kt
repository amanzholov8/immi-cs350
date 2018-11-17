package com.immi.thebrogrammers.immi

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

object ImmIParser {
  var client = OkHttpClient()
  val curr_host = "https://www.numbeo.com/api/currency_exchange_rates?api_key=1yj7w26vkf03z6"
  val cities_host = "https://www.numbeo.com/api/cities?api_key=1yj7w26vkf03z6"
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


}

class CitiesFeed(val cities: List<City>)
class CurrFeed(val exchange_rates: List<Currency>)