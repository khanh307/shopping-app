package com.example.shoppe.Activity

import android.annotation.SuppressLint
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.shoppe.Adapter.ProductAdapter
import com.example.shoppe.Data.Product
import com.example.shoppe.R
import com.example.shoppe.Util.Server
import org.json.JSONArray
import org.json.JSONObject

class ProductFragment : Fragment() {

    lateinit var adapterProduct: ProductAdapter
    var arrayProduct: ArrayList<Product> = ArrayList()
    var page: Int = 1
    lateinit var type: String
    lateinit var footer: View
    lateinit var viewMain: RecyclerView
    var gridLayoutManager = GridLayoutManager(context, 2)
    lateinit var handler: mHanler
    var limitdata = false

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_product, container, false)
        viewMain = view.findViewById(R.id.viewMain)
        var inflaterfooter: LayoutInflater = requireContext().getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        footer = inflaterfooter.inflate(R.layout.load_product_layout, null)
        handler = mHanler()
        var bundle = arguments
        if(bundle != null){
            type = bundle.getString("id", "-1")
        }
        getData(page)
        adapterProduct = ProductAdapter(requireContext(), arrayProduct)

        viewMain.setHasFixedSize(true)
        viewMain.layoutManager = gridLayoutManager
        viewMain.adapter = adapterProduct

        loadMoreProduct()
        return view
    }

    private fun loadMoreProduct() {
        viewMain.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                var visibleItemCount = gridLayoutManager.childCount
                var totalItemCount = gridLayoutManager.itemCount
                var firstItem = gridLayoutManager.findFirstVisibleItemPosition()
                if(firstItem + visibleItemCount == totalItemCount && totalItemCount != 0 && adapterProduct.isLoadingAdd == false && limitdata == false){
                    adapterProduct.isLoadingAdd = true
                    var t: ThreadData = ThreadData()
                    t.start()
                }

            }
        })
    }

    inner class mHanler: Handler() {
        override fun handleMessage(msg: Message) {
             if(msg.what == 0){
                adapterProduct.addFooterLoading()
             } else {
                 getData(++page)
                 adapterProduct.isLoadingAdd = false
             }
            super.handleMessage(msg)
        }
    }

    inner class ThreadData: Thread(){
        override fun run() {
            handler.sendEmptyMessage(0)
            Thread.sleep(2000)
            var message: Message = handler.obtainMessage()
            handler.sendMessage(message)
            super.run()
        }
    }


    private fun getData(page: Int) {
        var requestQueue: RequestQueue = Volley.newRequestQueue(context)
        var path: String = Server.pathProduct + page.toString()

        var stringRequest: StringRequest =object : StringRequest(Request.Method.POST, path, Response.Listener { response ->
            if(response != null && response.length > 0){
                adapterProduct.removeFooterLoading()
                var jsonArray: JSONArray = JSONArray(response)
                for(i in 0 until jsonArray.length()){
                    var jsonObject: JSONObject = jsonArray.getJSONObject(i)
                    var id = jsonObject.getInt("id")
                    var name = jsonObject.getString("name")
                    var image = jsonObject.getString("image")
                    image = image.replace("localhost:8012", Server.localhost)
                    var price = jsonObject.getDouble("price")
                    var detail = jsonObject.getString("detail")
                    var type = jsonObject.getInt("idtype")
                    arrayProduct.add(Product(id, name, image, price, detail, type))
                    adapterProduct.notifyDataSetChanged()
                }
            } else{
                limitdata = true
                adapterProduct.removeFooterLoading()
            }
        }, Response.ErrorListener {

        }){
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params.put("id", type)
                return params
            }
        }

        requestQueue.add(stringRequest)


    }

}