package com.example.shoppe.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shoppe.Data.Product
import com.example.shoppe.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_detail.*
import java.text.DecimalFormat

class ProductDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        var intent: Intent = intent
        var product: Product = intent.getSerializableExtra("object_product") as Product
        Picasso.get().load(product.image).into(imageproduct)
        nameproduct.text = product.name
        var decimalFormat: DecimalFormat = DecimalFormat("###,###,###");
        priceproduct.text = decimalFormat.format(product.price)
        detailproduct.text = product.detail
    }
}