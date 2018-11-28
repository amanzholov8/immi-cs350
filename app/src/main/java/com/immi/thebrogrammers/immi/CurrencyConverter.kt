package com.immi.thebrogrammers.immi

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import com.immi.thebrogrammers.immi.R.layout.activity_currency_converter

class CurrencyConverter : AppCompatActivity() {
  lateinit var from: Spinner
  lateinit var to: Spinner
  val currencies = arrayListOf<Currency>()
  lateinit var result: TextView
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(activity_currency_converter)
    val currencies = ImmIDatabase.currencies.map({ c -> c.cur_name })
    from = findViewById<Spinner>(R.id.fromCurrencies)
    to = findViewById<Spinner>(R.id.toCurrencies)

    from.adapter = ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, currencies)
    to.adapter = ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, currencies)

    result = findViewById<TextView>(R.id.converted_amount)


  }

  fun onConvert(view: View) {
    val fromResult = from.selectedItem.toString()
    val toResult = to.selectedItem.toString()

    val operand = getOperand(findViewById<EditText>(R.id.operand))

    result.text = ImmIDatabase.convertCurrencies(fromResult, toResult, operand).toString()
  }

  fun getOperand(operandEditText: EditText): Double {
    val operandText = getOperandText(operandEditText)
    return operandText.toDouble()
  }

  fun getOperandText(operandEditText: EditText): String {
    return operandEditText.text.toString()
  }


}
