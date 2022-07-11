package com.example.shoppe.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import android.widget.ViewFlipper
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.shoppe.Adapter.ProductAdapter
import com.example.shoppe.Data.Product
import com.example.shoppe.Listener.IClickItemListener
import com.example.shoppe.R
import com.example.shoppe.Util.Server
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*
import org.json.JSONObject

class Home_Fragment : Fragment(){
    var arrayNewProduct: ArrayList<Product> = ArrayList()
    lateinit var productAdapter: ProductAdapter;

    lateinit var viewFlipper: ViewFlipper;
    lateinit var mMainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_home, container, false)
        var viewProduct: RecyclerView = view.findViewById(R.id.viewProduct)
        viewFlipper = view.findViewById(R.id.viewFlipper)
        productAdapter = ProductAdapter(requireContext(), arrayNewProduct,
            object : IClickItemListener {
                override fun clickItem(product: Product) {
                    mMainActivity.intentActivity(product)
                }
            })
        viewProduct.setHasFixedSize(true)
        viewProduct.layoutManager = GridLayoutManager(context, 2)

        mMainActivity = activity as MainActivity

        viewProduct.adapter = productAdapter

        getNewProduct()
        actionViewFlipper()

        Log.d("CCC", "onCreateView")
        return view
    }

    private fun getNewProduct() {
        arrayNewProduct.clear()
        var requestQueue: RequestQueue = Volley.newRequestQueue(context)
        var jsonArrayRequest: JsonArrayRequest = JsonArrayRequest(Server.pathNewProduct, Response.Listener { response->
            if(response != null){
                for(i in 0 until response.length()){
                    var jsonObject: JSONObject = response.getJSONObject(i)
                    var id = jsonObject.getInt("id")
                    var name = jsonObject.getString("name")
                    var image = jsonObject.getString("image")
                    image = image.replace("localhost:8012", Server.localhost)
                    var price = jsonObject.getDouble("price")
                    var detail = jsonObject.getString("detail")
                    var type = jsonObject.getInt("idtype")
                    arrayNewProduct.add(Product(id, name, image, price, detail, type))
                    productAdapter.notifyDataSetChanged()
                }
            }
        }, Response.ErrorListener {

        })
        requestQueue.add(jsonArrayRequest)
    }

    private fun actionViewFlipper(){
        var array: ArrayList<String> = ArrayList()
        array.add("https://www.thuengay.vn/uploads/770x433/dd5787fa0c9306323b7176ce91a4d31ff6041c4a2.jpg")
        array.add("https://cdn.tgdd.vn/hoi-dap/1355217/banner-tgdd-800x300.jpg")
        array.add("https://www.xtmobile.vn/vnt_upload/news/11_2018/15/tuan-le-phu-kien-khuyen-mai-pin-du-phong-xtmobile.jpg")

        for (i in 0..array.size-1){
            var imageview = ImageView(context)
            Picasso.get().load(array.get(i)).into(imageview)
            imageview.scaleType = ImageView.ScaleType.CENTER_CROP
            viewFlipper.addView(imageview)
        }
        viewFlipper.flipInterval = 5000
        viewFlipper.isAutoStart = true
        var animation_in: Animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_right)
        var animation_out: Animation = AnimationUtils.loadAnimation(context, R.anim.slide_out_right)
        viewFlipper.setInAnimation(animation_in)
        viewFlipper.setOutAnimation(animation_out)
    }


}