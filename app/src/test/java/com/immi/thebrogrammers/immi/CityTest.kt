package com.immi.thebrogrammers.immi

import org.junit.Assert.assertEquals
import org.junit.Test

class CityTest {

  @Test
  fun compareCities() {
    val temp = ImmIDatabase()
    val city1 = temp.cities[0]
    val city2 = temp.cities[1]
    val qindex = temp.qindices[0]

    city1.qIndices.put(qindex.qname, 65.0)
    city2.qIndices.put(qindex.qname, 55.0)

    val actual1 = city1.compareCities(city2, qindex)
    val expected1 = "Healthcare system's quality in Seattle compared to Dubai is higher.\n"

    val actual2 = city2.compareCities(city1, qindex)
    val expected2 = "Healthcare system's quality in Dubai compared to Seattle is lower.\n"

    assertEquals("comparingCities by index failed", actual1, expected1)
    assertEquals("comparingCities by index failed", actual2, expected2)
  }
}