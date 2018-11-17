package com.immi.thebrogrammers.immi

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

object ImmIParser {
  var client = OkHttpClient()

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
}