package com.immi.thebrogrammers.immi

import com.google.gson.annotations.SerializedName

class Currency(
  @SerializedName("currency") val cur_name: String,
  @SerializedName("one_usd_to_currency") val usd_to_cur: Double,
  @SerializedName("one_eur_to_currency") val eur_to_cur: Double)