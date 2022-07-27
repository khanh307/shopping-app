package com.example.shoppe.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shoppe.Data.Product
import com.example.shoppe.Data.Product_Cart
import com.example.shoppe.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.layout_item_in_cart.*
import java.text.DecimalFormat

class ProductDetail : AppCompatActivity() {
    lateinit var product: Product
    var productCart: Product_Cart? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        var bundle: Bundle = intent.extras!!
        product = bundle?.getSerializable("object_product") as Product
        Picasso.get().load(product.image).into(imageproduct)
        nameproduct.text = product.name
        var decimalFormat: DecimalFormat = DecimalFormat("###,###,###");
        priceproduct.text = "đ"+decimalFormat.format(product.price)
        detailproduct.text = product.detail

        actionToolBar()
        setAction()
    }

    private fun setAction(){
        addToCart.setOnClickListener{

        }

        var num: Int = amount.text.toString().toInt()
        subBtn.setOnClickListener{
            if(num > 1){
                num--
                amount.text = num.toString()
                caculator.text = priceproduct.text.toString() +" x " + num.toString()
                var decimalFormat: DecimalFormat = DecimalFormat("###,###,###");
                var re = product.price * num
                result.text = decimalFormat.format(re)
            }
        }
        addBtn.setOnClickListener{
            if(num < 10){
                num++
                amount.text = num.toString()
                caculator.text = priceproduct.text.toString() +" x " + num.toString()+": "
                var decimalFormat: DecimalFormat = DecimalFormat("###,###,###");
                var re = product.price * num
                result.text = decimalFormat.format(re)
            }
        }
        caculator.text = priceproduct.text.toString() +" x " + num.toString()
        var decimalFormat: DecimalFormat = DecimalFormat("###,###,###");
        var re = product.price * num
        result.text = decimalFormat.format(re)
    }

    private fun actionToolBar() {
        setSupportActionBar(backBtn)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        backBtn.setNavigationOnClickListener{
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_enter_right, R.anim.slide_exit_right)
    }
}