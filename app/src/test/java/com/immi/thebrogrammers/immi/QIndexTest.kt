package com.immi.thebrogrammers.immi

import org.junit.Assert.assertEquals
import org.junit.Test

class QIndexTest {

  @Test
  fun getFullDescription() {

    val temp1 = QIndex(
      qname = "health_care_index",
      descr = "Healthcare system's quality ",
      seps = arrayOf(55.0, 70.0),
      quals = arrayOf("bad", "moderate", "good")
    )

    val response1 = temp1.getFullDescription(50.0)
    val response2 = temp1.getFullDescription(60.0)
    val response3 = temp1.getFullDescription(80.0)
    val expected1 = "Healthcare system's quality is bad.\n"
    val expected2 = "Healthcare system's quality is moderate.\n"
    val expected3 = "Healthcare system's quality is good.\n"

    assertEquals("gettingFullDescription for bad value failed", expected1, response1)
    assertEquals("gettingFullDescription for moderate value failed", expected2, response2)
    assertEquals("gettingFullDescription for good value failed", expected3, response3)
  }

  @Test
  fun getCompareDescription() {
    val temp = ImmIDatabase()
    val city1 = temp.cities[0]
    val city2 = temp.cities[1]
    val qindex = temp.qindices[0]
    val actual1 = qindex.getCompareDescription(city1, city2)
    val expected1 = "Healthcare system's quality in Seattle compared to Dubai is "
    assertEquals("gettingCompareDescription of cities for index failed", expected1, actual1)
  }
}