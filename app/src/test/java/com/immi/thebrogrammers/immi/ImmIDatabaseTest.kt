package com.immi.thebrogrammers.immi

import org.junit.Assert.assertEquals
import org.junit.Test

class ImmIDatabaseTest() {

    @Test
    fun convertCurrencies() {

        val temp1 = Currency("AED",3.6731809999999996,4.190760928470784)
        val temp2 = Currency("FKP", 0.769309, 0.8777106543676804)
        val temp3 = Currency("KRW", 1123.83, 1282.1864357469237)
        val temp4 = Currency("KZT", 370.490964, 422.69603819759385)
        val temp = ImmIDatabase()
        temp.curr_map.put("AED", temp1)
        temp.curr_map.put("FKP", temp2)
        temp.curr_map.put("KRW", temp3)
        temp.curr_map.put("KZT", temp4)
        val actual1 = temp.convertCurrencies("KRW", "KZT", 3000.0)
        val expected1 = 989.00446
        val actual2 = temp.convertCurrencies("KRW", "AED", 3000.0)
        val expected2 = 9.87
        val actual3 = temp.convertCurrencies("FKP", "KZT", 3000.0)
        val expected3 = 1444767.82670000
        val actual4 = temp.convertCurrencies("AED", "FKP", 3000.0)
        val expected4 = 628.297
        assertEquals("Convertion from KRW to KZT failed", expected1, actual1, 0.1)
        assertEquals("Convertion from KRW to AED failed", expected2, actual2, 0.1)
        assertEquals("Convertion from FKP to KZT failed", expected3, actual3, 0.1)
        assertEquals("Convertion from AED to FKP failed", expected4, actual4, 0.1)
    }

    @Test
    fun getQIndexByName() {
        val temp = ImmIDatabase()
        val temp1 = temp.qindices[0]
        val temp2 = temp.qindices[1]
        val actual1 = temp.getQIndexByName(temp1.qname)
        val actual2 = temp.getQIndexByName(temp2.qname)
        assertEquals(actual1, temp1)
        assertEquals(actual2, temp2)
    }

}