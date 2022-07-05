package com.example.shoppe.Activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ViewFlipper
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppe.Adapter.ProductAdapter
import com.example.shoppe.Data.Product
import com.example.shoppe.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*

class Home_Fragment : Fragment() {
    var arrayProduct: ArrayList<Product> = ArrayList()
    lateinit var productAdapter: ProductAdapter;

    lateinit var viewFlipper: ViewFlipper;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_home, container, false)
        var viewProduct: RecyclerView = view.findViewById(R.id.viewProduct)
        viewFlipper = view.findViewById(R.id.viewFlipper)
        productAdapter = ProductAdapter(requireContext(), arrayProduct)
        viewProduct.setHasFixedSize(true)
        viewProduct.layoutManager = GridLayoutManager(context, 2)

        viewProduct
        viewProduct.adapter = productAdapter
        addItem()
        actionViewFlipper()
        return view
    }

    private fun addItem(){
        var image = "https://cdn01.dienmaycholon.vn/filewebdmclnew/DMCL21/Picture//Apro/Apro_product_30009/dien-thoai-sams_main_346_1020.png.webp"
        arrayProduct.add(Product(0, "Điện thoại Samsung Galaxy S21 Ultra 5G", image, 1000000F, "AAAA", 2))
        arrayProduct.add(Product(1, "Điện thoại Samsung Galaxy S21 Ultra 5G", image, 1000000F, "AAAA", 2))
        arrayProduct.add(Product(2, "Điện thoại Samsung Galaxy S21 Ultra 5G", image, 1000000F, "AAAA", 2))
        arrayProduct.add(Product(3, "Điện thoại Samsung Galaxy S21 Ultra 5G", image, 1000000F, "AAAA", 2))
        arrayProduct.add(Product(4, "Điện thoại Samsung Galaxy S21 Ultra 5G", image, 1000000F, "AAAA", 2))
        arrayProduct.add(Product(5, "Điện thoại Samsung Galaxy S21 Ultra 5G", image, 1000000F, "AAAA", 2))
        productAdapter.notifyDataSetChanged()
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