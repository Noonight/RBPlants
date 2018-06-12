package com.arkhipov.ayur.rbplants

import com.google.firebase.Timestamp
import org.junit.Test

import org.junit.Assert.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun timeTest() {
        val timestamp = Timestamp.now()
        println(timestamp.seconds * 1000)
        //val calendar = Calendar.getInstance()
        val date = Date(timestamp.seconds * 1000)
        println("$date current time in time stamp")
        val var0 = 700L
        val var1 = 20L
        println(var0 + var1)
    }
}
