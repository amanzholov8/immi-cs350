package com.immi.thebrogrammers.immi

class City : GeoObject {
  val country: Country
  var qIndices: MutableMap<String, Double>

  constructor(geo_name: String, lat: Float, lon: Float, country: Country, indices: MutableMap<String, Double> = mutableMapOf<String, Double>()) :
    super(geo_name, lat, lon) {
    this.country = country
    this.qIndices = indices
  }
}