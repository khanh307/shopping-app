package com.example.shoppe.Activity

import android.content.Intent
import android.location.GnssAntennaInfo.Listener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.shoppe.Data.Product
import com.example.shoppe.Data.Product_Cart
import com.example.shoppe.R
import com.example.shoppe.Util.Server
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.layout_item_in_cart.*
import org.json.JSONArray
import org.json.JSONObject
import java.text.DecimalFormat

class ProductDetail : AppCompatActivity() {
    lateinit var product: Product
    var arrayProduct: ArrayList<Product_Cart> = ArrayList()

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
        getData()
        actionToolBar()
        setAction()

    }

    private fun getData(){
        var requestQueue = Volley.newRequestQueue(this);
        var stringRequest: StringRequest = object: StringRequest(Request.Method.POST, Server.pathCart, Response.Listener{
                response ->
            if(response != null && response.length > 0){
                var jsonArray = JSONArray(response)
                for(i in 0 until jsonArray.length()){
                    var jsonObject: JSONObject = jsonArray.getJSONObject(i)
                    var id = jsonObject.getInt("id")
                    var name = jsonObject.getString("name")
                    var image = jsonObject.getString("image")
                    image = image.replace("localhost:8012", Server.localhost)
                    var price = jsonObject.getDouble("price")
                    var amount = jsonObject.getString("amount")
                    arrayProduct.add(Product_Cart(id, name, image, price, amount.toString().toInt()))
                }
            }
        }, Response.ErrorListener{
        }){
            override fun getParams(): Map<String, String>? {
                val params = HashMap<String, String>()
                params.put("user", "khanhtq")
                return params
            }
        }
        requestQueue.add(stringRequest)

    }

    private fun setAction(){
        addToCart.setOnClickListener{
            var exists = false
            for(i in 0 until arrayProduct.size){
                if(product.id == arrayProduct.get(i).id){
                    var sl = amount.text.toString().toInt()
                    if(arrayProduct.get(i).amount < 10){
                        arrayProduct.get(i).amount += sl;
                    }
                    exists = true
                }
            }
            if(exists == false){
                var sl = amount.text.toString().toInt()
                arrayProduct.add(Product_Cart(product.id, product.name, product.image, product.price, sl))
            }
            var str: String = ""
            var str2: String = ""
            for(i in 0 until arrayProduct.size){
                str += arrayProduct.get(i).id.toString() +","
                str2 += arrayProduct.get(i).amount.toString() +","
            }
            str = str.substring(0, str.length-1)
            str2 = str2.substring(0, str2.length-1)
            Log.d("HHH", str)
            Log.d("HHH", str2)

            updateCart(str, str2)
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

    private fun updateCart(str: String, str2: String){
        var requestQueue = Volley.newRequestQueue(this)
        var stringRequest = object: StringRequest(Request.Method.POST, Server.pathUpdateCart, Response.Listener {
            response ->
            if(response.trim().equals("success")){
                Toast.makeText(this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show()
            }
        }, Response.ErrorListener {
            Toast.makeText(this, "Lỗi", Toast.LENGTH_SHORT).show()
        }){
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String, String>()
                params.put("user", "khanhtq");
                params.put("str", str);
                params.put("str2", str2)
                return params
            }
        }
        requestQueue.add(stringRequest)
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
        finish()
    }
}