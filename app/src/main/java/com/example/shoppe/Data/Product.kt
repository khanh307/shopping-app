package com.example.shoppe.Data

data class Product(
    var id: Int,
    var name: String,
    var image: String,
    var price: Double,
    var detail: String,
    var type: Int
): java.io.Serializable {
}