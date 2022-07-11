package com.example.shoppe.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppe.Data.Product
import com.example.shoppe.Data.Product_Cart
import com.example.shoppe.R
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

class CartAdapter(var context: Context, var arrayProduct: ArrayList<Product_Cart>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_ITEM = 1
    private val TYPE_TITLE = 2

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var checkBox: CheckBox
        var imageView: ImageView
        var tvName: TextView
        var tvPrice: TextView
        var addBtn_cart: ImageButton
        var subBtn_cart: ImageButton
        var amount_cart: TextView
        init {
            checkBox = itemView.findViewById(R.id.checkbox)
            imageView = itemView.findViewById(R.id.image_item_cart)
            tvName = itemView.findViewById(R.id.name_item_cart)
            addBtn_cart = itemView.findViewById(R.id.btnAdd_cart)
            subBtn_cart = itemView.findViewById(R.id.btnSub_cart)
            amount_cart = itemView.findViewById(R.id.amount_cart)
            tvPrice = itemView.findViewById(R.id.price_item_cart)
        }
    }

    class TitleHodler(itemView: View) : RecyclerView.ViewHolder(itemView){
        var title: TextView
        init {
            title = itemView.findViewById(R.id.title)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(arrayProduct.get(position).isTitle == true){
            return TYPE_TITLE
        }
        return TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == TYPE_ITEM){
            var view: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_in_cart, null)
            var holder: ItemHolder = ItemHolder(view)
            return holder
        } else {
            var view: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_title_in_cart, null)
            var holder: TitleHodler = TitleHodler(view)
            return holder
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder.itemViewType == TYPE_ITEM){
            var item: Product_Cart = arrayProduct.get(position)
            var itemHodler = holder as ItemHolder
            itemHodler.tvName.text = item.name
            var decimalFormat: DecimalFormat = DecimalFormat("###,###,###");
            itemHodler.tvPrice.text = "đ"+decimalFormat.format(item.price)
            itemHodler.amount_cart.text = item.amount.toString()
            Picasso.get().load(item.image).into(holder.imageView)
        } else {
            var item: Product_Cart = arrayProduct.get(position)
            var titleHolder = holder as TitleHodler
            titleHolder.title.text = item.type
        }
    }

    override fun getItemCount(): Int {
        return arrayProduct.size
    }
}