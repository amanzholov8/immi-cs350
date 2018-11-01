package com.immi.thebrogrammers.immi

class Country : GeoObject {
    val currency: Currency

    constructor(geo_name: String, lat: Float, lon: Float, currency: Currency) :
            super(geo_name, lat, lon) {
        this.currency = currency
    }
}