package com.example.taller1_restaurante

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.taller1_restaurante.modelo.Dish
import com.example.taller1_restaurante.modelo.Order

class MainActivity : AppCompatActivity() {

    private lateinit var pastelQuantity: EditText
    private lateinit var cazuelaQuantity: EditText
    private lateinit var subtotalValue: TextView
    private lateinit var tipSwitch: Switch
    private lateinit var tipValue: TextView
    private lateinit var totalValue: TextView

    private lateinit var order: Order

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pastelQuantity = findViewById(R.id.qltyPastel)
        cazuelaQuantity = findViewById(R.id.qltyCazuela)
        subtotalValue = findViewById(R.id.valueSubTotal)
        tipSwitch = findViewById(R.id.tipSwitch)
        tipValue = findViewById(R.id.propinaValue)
        totalValue = findViewById(R.id.totalValue)

        val dishes = listOf(
            Dish("Pastel de Choclo", 5000),
            Dish("Cazuela de Vacuno", 6000)
        )

        order = Order(dishes)

        pastelQuantity.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateQuantities()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        cazuelaQuantity.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateQuantities()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        tipSwitch.setOnCheckedChangeListener { _, _ -> updateTotals() }

        updateTotals()
    }

    private fun updateQuantities() {
        val pastelQty = pastelQuantity.text.toString().toIntOrNull() ?: 0
        val cazuelaQty = cazuelaQuantity.text.toString().toIntOrNull() ?: 0

        order.setQuantity(order.getDish("Pastel de Choclo")!!, pastelQty)
        order.setQuantity(order.getDish("Cazuela de Vacuno")!!, cazuelaQty)

        updateTotals()
    }

    private fun updateTotals() {
        val subtotal = order.getSubtotal()
        val tip = order.getTip()
        val total = order.getTotal(tipSwitch.isChecked)

        subtotalValue.text = order.getFormattedCurrency(subtotal)
        tipValue.text = order.getFormattedCurrency(if (tipSwitch.isChecked) tip else 0)
        totalValue.text = order.getFormattedCurrency(total)
    }
}
