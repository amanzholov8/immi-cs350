package com.immi.thebrogrammers.immi

import org.junit.Assert.assertEquals
import org.junit.Test

class QIndexTest {

    @Test
    fun getFullDescription() {

        val temp1 = QIndex(
                qname = "health_care_index",
                descr = "Healthcare system in this city is ",
                seps = arrayOf(55.0, 70.0),
                quals = arrayOf("bad", "moderate", "good")
        )

        val response1 = temp1.getFullDescription(50.0)
        val response2 = temp1.getFullDescription(60.0)
        val response3 = temp1.getFullDescription(80.0)
        val expected1 = "Healthcare system in this city is bad.\n"
        val expected2 = "Healthcare system in this city is moderate.\n"
        val expected3 = "Healthcare system in this city is good.\n"

        assertEquals("gettingFullDescription for bad value failed", expected1, response1)
        assertEquals("gettingFullDescription for moderate value failed", expected2, response2)
        assertEquals("gettingFullDescription for good value failed", expected3, response3)


    }
}