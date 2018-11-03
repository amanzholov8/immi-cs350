package com.immi.thebrogrammers.immi

class City : GeoObject {
  val country: Country

  constructor(geo_name: String, lat: Float, lon: Float, country: Country, indices: Map<String, Double> = mapOf<String, Double>()) :
    super(geo_name, lat, lon) {
    this.country = country
  }
}