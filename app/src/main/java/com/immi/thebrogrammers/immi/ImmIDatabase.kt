package com.immi.thebrogrammers.immi

class ImmIDatabase {

  val cities = arrayListOf<City>()
  val countries = arrayListOf<Country>()
  val currencies = arrayListOf<Currency>()
  val curr_map = mutableMapOf<String, Currency>()


  init {
    //NOTE: Init the arrays with some dummy data

    //Currencies' initialization
    currencies.add(
      Currency(
        cur_name = "USD",
        usd_to_cur = 1.0f,
        eur_to_cur = 1.14f
      ))

    //Countries initialization
    countries.add(
      Country(
        geo_name = "USA",
        lat = 0f,
        lon = 0f,
        currency = currencies[0]
      ))

    //Cities initialization
    cities.add(
      City(
        geo_name = "Seattle",
        lat = 34.22f,
        lon = 12.1f,
        country = countries[0]))
  }

  fun findCurrencyByCode(curr_code: String): Currency? {
    if (curr_map.containsKey(curr_code)) {
      return curr_map[curr_code]
    } else {
      throw Exception("Currency code ${curr_code} doesn't exist")
    }
  }

  fun convertCurrencies(from: Currency, to: Currency) {
  }
}