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
        currencies.add(
                Currency(
                        cur_name = "AED",
                        usd_to_cur = 3.6731809999999996f,
                        eur_to_cur = 4.190760928470784f
                ))
        currencies.add(
                Currency(
                        cur_name = "KZT",
                        usd_to_cur = 370.490964f,
                        eur_to_cur = 422.69603819759385f
                ))
        currencies.add(
                Currency(
                        cur_name = "RUB",
                        usd_to_cur = 65.7162f,
                        eur_to_cur = 74.97612650385912f
                ))
        currencies.add(
                Currency(
                        cur_name = "KRW",
                        usd_to_cur = 1123.83f,
                        eur_to_cur = 1282.1864357469237f
                ))
        currencies.add(
                Currency(
                        cur_name = "AUD",
                        usd_to_cur = 1.381104f,
                        eur_to_cur = 1.5757123543203329f
                ))
        currencies.add(
                Currency(
                        cur_name = "CAD",
                        usd_to_cur = 1.307928f,
                        eur_to_cur = 1.4922252836582068f
                ))
        currencies.add(
                Currency(
                        cur_name = "AZN",
                        usd_to_cur = 1.7025f,
                        eur_to_cur = 1.9423955641503943f
                ))


        //Countries initialization
        countries.add(
                Country(
                        geo_name = "USA",
                        lat = 0f,
                        lon = 0f,
                        currency = currencies[0]
                ))
        countries.add(
                Country(
                        geo_name = "United Arab Emirates",
                        lat = 0f,
                        lon = 0f,
                        currency = currencies[1]
                ))
        countries.add(
                Country(
                        geo_name = "Kazakhstan",
                        lat = 0f,
                        lon = 0f,
                        currency = currencies[2]
                ))
        countries.add(
                Country(
                        geo_name = "Russia",
                        lat = 0f,
                        lon = 0f,
                        currency = currencies[3]
                ))
        countries.add(
                Country(
                        geo_name = "South Korea",
                        lat = 0f,
                        lon = 0f,
                        currency = currencies[4]
                ))
        countries.add(
                Country(
                        geo_name = "Australia",
                        lat = 0f,
                        lon = 0f,
                        currency = currencies[5]
                ))
        countries.add(
                Country(
                        geo_name = "Canada",
                        lat = 0f,
                        lon = 0f,
                        currency = currencies[6]
                ))
        countries.add(
                Country(
                        geo_name = "Azerbaijan",
                        lat = 0f,
                        lon = 0f,
                        currency = currencies[7]
                ))





        cities.add(
                City(
                        geo_name = "Seattle",
                        lat = 34.22f,
                        lon = 12.1f,
                        country = countries[0]
                ))

        cities.add(
                City(
                        geo_name = "Dubai",
                        lat = 25.2048493f,
                        lon = 55.2707828f,
                        country = countries[1]
                ))

        cities.add(
                City(
                        geo_name = "Almaty",
                        lat = 43.2220146f,
                        lon = 76.8512485f,
                        country = countries[2]
                ))

        cities.add(
                City(
                        geo_name = "Moscow",
                        lat = 55.755826f,
                        lon = 37.6173f,
                        country = countries[3]
                ))

        cities.add(
                City(
                        geo_name = "Seoul",
                        lat = 37.566535f,
                        lon = 126.9779692f,
                        country = countries[4]
                ))

        cities.add(
                City(
                        geo_name = "Melbourne",
                        lat = -37.814251f,
                        lon = 144.963169f,
                        country = countries[5]
                ))

        cities.add(
                City(
                        geo_name = "Ottawa",
                        lat = 45.4215296f,
                        lon = -75.69719309999999f,
                        country = countries[6]
                ))


        cities.add(
                City(
                        geo_name = "Baku",
                        lat = 40.40926169999999f,
                        lon = 49.8670924f,
                        country = countries[7]
                ))
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