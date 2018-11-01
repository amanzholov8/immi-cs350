package com.immi.thebrogrammers.immi

class ImmIDatabase(var curr_map: Map<String, Currency>) {

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