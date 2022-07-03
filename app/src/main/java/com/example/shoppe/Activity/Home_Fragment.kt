package com.example.shoppe.Activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppe.Adapter.ProductAdapter
import com.example.shoppe.Data.Product
import com.example.shoppe.R

class Home_Fragment : Fragment() {
    var arrayProduct: ArrayList<Product> = ArrayList()
    lateinit var productAdapter: ProductAdapter;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_home, container, false)
        var viewProduct: RecyclerView = view.findViewById(R.id.viewProduct)
        productAdapter = ProductAdapter(requireContext(), arrayProduct)
        viewProduct.setHasFixedSize(true)
        viewProduct.layoutManager = GridLayoutManager(context, 2)

        viewProduct
        viewProduct.adapter = productAdapter
        addItem()
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


}