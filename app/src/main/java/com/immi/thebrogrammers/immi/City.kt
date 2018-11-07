package com.immi.thebrogrammers.immi

class City : GeoObject {
  val country: Country
  var qIndices: MutableMap<String, Double>

  constructor(geo_name: String, lat: Float, lon: Float, country: Country, indices: MutableMap<String, Double> = mutableMapOf<String, Double>()) :
    super(geo_name, lat, lon) {
    this.country = country
    this.qIndices = indices
  }

  fun compareCities(city2: City, qindex: QIndex): String {

    val comp_adj = if (this.qIndices.get(qindex.qname)!! > city2.qIndices.get(qindex.qname)!!)
      "higher" else "lower"

    return (qindex.getCompareDescription(this, city2) + comp_adj + ".\n")
  }
}