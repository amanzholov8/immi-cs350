package com.immi.thebrogrammers.immi

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_compare_cities.*

class CompareCities : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compare_cities)
    }

    fun compareTwoCities(view: View) {

        val cityInput1: EditText = findViewById<EditText>(R.id.inputCity1)
        val cityInput2: EditText = findViewById<EditText>(R.id.inputCity2)
        val cityName1 = cityInput1.text.toString()
        val cityName2 = cityInput2.text.toString()
        println(cityName1)
        println(cityName2)
        //textCity1.text = city1
        //textCity2.text = city2
        val city1 = db.getCityByName(cityName1)
        val city2 = db.getCityByName(cityName2)
        val qindex = db.qindices[0]
        val ans = city1?.compareCities(city2!!, qindex)
        answerText.text = ans
    }
}
