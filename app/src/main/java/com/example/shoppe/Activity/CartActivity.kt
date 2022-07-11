package com.example.shoppe.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppe.Adapter.CartAdapter
import com.example.shoppe.Data.Product
import com.example.shoppe.Data.Product_Cart
import com.example.shoppe.R
import kotlinx.android.synthetic.main.activity_cart.*

class CartActivity : AppCompatActivity() {
    var arrayProduct: ArrayList<Product_Cart> = ArrayList()
    lateinit var adapterCart: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        main_content.setHasFixedSize(true)
        var linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        main_content.layoutManager = linearLayoutManager

        adapterCart = CartAdapter(this, arrayProduct)

        main_content.adapter = adapterCart
        getData()

        btnBack_cart.setOnClickListener {
            onBackPressed()
        }
    }

    fun getData(){
        var image = "http://192.168.1.30:8012/shopee/images/TaiNghe/sp1.png"
        arrayProduct.add(Product_Cart(1, "Khánh", image, "1000000".toString().toDouble(), 1, "Điện thoại", true))
        arrayProduct.add(Product_Cart(2, "Khánh", image, "1000000".toString().toDouble(), 2, "Điện thoại", false))
        arrayProduct.add(Product_Cart(3, "Khánh", image, "1000000".toString().toDouble(), 3, "Điện thoại", false))

        arrayProduct.add(Product_Cart(4, "Khánh", image, "1000000".toString().toDouble(), 4, "Tai nghe", true))
        arrayProduct.add(Product_Cart(5, "Khánh", image, "1000000".toString().toDouble(), 2, "Tai nghe", false))
        arrayProduct.add(Product_Cart(6, "Khánh", image, "1000000".toString().toDouble(), 1, "Tai nghe",false))

        arrayProduct.add(Product_Cart(7, "Khánh", image, "1000000".toString().toDouble(), 1, "Cáp sạc", true))
        arrayProduct.add(Product_Cart(8, "Khánh", image, "1000000".toString().toDouble(), 1, "Cáp sạc", false))
        arrayProduct.add(Product_Cart(9, "Khánh", image, "1000000".toString().toDouble(), 1, "Cáp sạc", false))
        adapterCart.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_enter_right, R.anim.slide_exit_right)
    }
}