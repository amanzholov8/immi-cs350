package com.immi.thebrogrammers.immi

import com.google.gson.annotations.SerializedName

open class GeoObject(
  @SerializedName("city") val geo_name: String,
  @SerializedName("latitude") val lat: Float,
  @SerializedName("longitude") val lon: Float)