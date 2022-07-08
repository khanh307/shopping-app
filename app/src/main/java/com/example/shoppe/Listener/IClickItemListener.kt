package com.example.shoppe.Listener

import com.example.shoppe.Data.Product

interface IClickItemListener {
    fun clickItem(product: Product)
}