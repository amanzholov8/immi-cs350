package com.immi.thebrogrammers.immi

class City : GeoObject {
    val country: Country

    constructor(geo_name: String, lat: Float, lot: Float, country: Country) :
            super(geo_name, lat, lot) {
        this.country = country
    }
}