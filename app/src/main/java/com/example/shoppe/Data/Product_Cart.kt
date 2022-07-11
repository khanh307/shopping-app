package com.example.shoppe.Data

import android.icu.util.CurrencyAmount

data class Product_Cart(var id: Int, var name: String,var image: String, var price: Double, var amount: Int, var type: String, var isTitle: Boolean) {
}