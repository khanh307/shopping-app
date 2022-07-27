package com.example.shoppe.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.shoppe.Adapter.CartAdapter
import com.example.shoppe.Data.Product
import com.example.shoppe.Data.Product_Cart
import com.example.shoppe.R
import com.example.shoppe.Util.Server
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

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
                    adapterCart.notifyDataSetChanged()
                }
            }
        }, Response.ErrorListener {
        }){
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String, String>()
                params.put("user", "khanhtq")
                return params
            }
        }
        requestQueue.add(stringRequest)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_enter_right, R.anim.slide_exit_right)
    }
}