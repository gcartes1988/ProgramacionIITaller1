package com.example.taller1_restaurante.modelo

import java.text.NumberFormat
import java.util.*

class Order(private val dishes: List<Dish>) {
    private val quantities = mutableMapOf<Dish, Int>()

    init {
        for (dish in dishes) {
            quantities[dish] = 0
        }
    }

    fun setQuantity(dish: Dish, quantity: Int) {
        quantities[dish] = quantity
    }

    fun getDish(name: String): Dish? {
        return dishes.find { it.name == name }
    }

    fun getSubtotal(): Int {
        var subtotal = 0
        for ((dish, quantity) in quantities) {
            subtotal += dish.price * quantity
        }
        return subtotal
    }

    fun getTip(): Int {
        return (getSubtotal() * 0.1).toInt()
    }

    fun getTotal(includeTip: Boolean): Int {
        val subtotal = getSubtotal()
        return if (includeTip) {
            subtotal + getTip()
        } else {
            subtotal
        }
    }

    fun getFormattedCurrency(amount: Int): String {
        val format = NumberFormat.getCurrencyInstance(Locale("es", "CL"))
        return format.format(amount)
    }
}
