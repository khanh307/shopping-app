package com.example.shoppe.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppe.Adapter.CartAdapter
import com.example.shoppe.Data.Product
import com.example.shoppe.R
import kotlinx.android.synthetic.main.activity_cart.*

class CartActivity : AppCompatActivity() {
    var arrayProduct: ArrayList<Product> = ArrayList()
    lateinit var adapterCart: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        var linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)
        main_content.layoutManager = linearLayoutManager

        adapterCart = CartAdapter(this, arrayProduct)

        main_content.adapter = adapterCart
        getData()
    }

    fun getData(){
        var image = "http://192.168.1.30:8012/shopee/images/TaiNghe/sp1.png"
        arrayProduct.add(Product(1, "Khánh", "http://192.168.1.30:8012/shopee/images/TaiNghe/sp1.png" , "1000000".toString().toDouble(), "aaa", 2))
        arrayProduct.add(Product(2, "Khánh", image, "1000000".toString().toDouble(), "aaa", 2))
        arrayProduct.add(Product(3, "Khánh", image, "1000000".toString().toDouble(), "aaa", 2))

        arrayProduct.add(Product(4, "Khánh", image, "1000000".toString().toDouble(), "aaa", 2))
        arrayProduct.add(Product(5, "Khánh", image, "1000000".toString().toDouble(), "aaa", 2))
        arrayProduct.add(Product(6, "Khánh", image, "1000000".toString().toDouble(), "aaa", 2))

        arrayProduct.add(Product(7, "Khánh", image, "1000000".toString().toDouble(), "aaa", 2))
        arrayProduct.add(Product(8, "Khánh", image, "1000000".toString().toDouble(), "aaa", 2))
        arrayProduct.add(Product(9, "Khánh", image, "1000000".toString().toDouble(), "aaa", 2))
        adapterCart.notifyDataSetChanged()
    }
}