package com.immi.thebrogrammers.immi

class QIndex(
  val qname: String,
  val descr: String,
  val seps: Array<Double>,
  val quals: Array<String>,
  val compDescr: String = "") {


  fun getFullDescription(value: Double): String {
    val msg: String
    var ind = 0
    while (ind < this.seps.size) {
      if (value < this.seps[ind]) {
        break
      }
      ind += 1
    }
    msg = this.quals[ind]
    return (descr + "is " + msg + ".\n")
  }

  fun getCompareDescription(c1: City, c2: City): String {
    return (descr + "in ${c1.geo_name} compared to ${c2.geo_name} is ")
  }


}
