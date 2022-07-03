package com.example.shoppe.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppe.Data.Product
import com.example.shoppe.R
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

class ProductAdapter(var context: Context, var arrayProduct: ArrayList<Product>): RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    class ProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView
        var tvPrice: TextView
        var tvName: TextView
        init {
            imageView = itemView.findViewById(R.id.productimage)
            tvPrice = itemView.findViewById(R.id.productprice)
            tvName = itemView.findViewById(R.id.productname)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_product, null)
        var holder: ProductHolder = ProductHolder(view)


        return holder
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        var item: Product = arrayProduct.get(position)
        holder.tvName.text = item.name
        var decimalFormat: DecimalFormat = DecimalFormat("###,###,###");
        holder.tvPrice.text = decimalFormat.format(item.price)
        Picasso.get().load(item.image).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return arrayProduct.size
    }


}