package com.example.shoppe.Adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.shoppe.Data.Product
import com.example.shoppe.Listener.IClickItemListener
import com.example.shoppe.R
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

class ProductAdapter(var context: Context, var arrayProduct: ArrayList<Product>,var iClickItemListener: IClickItemListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_ITEM = 1;
    private val TYPE_LOADING = 2;
    var isLoadingAdd: Boolean = false

    class ProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView
        var tvPrice: TextView
        var tvName: TextView
        var tvDetail: TextView
        var layoutItem: LinearLayout
        init {
            imageView = itemView.findViewById(R.id.productimage)
            tvPrice = itemView.findViewById(R.id.productprice)
            tvName = itemView.findViewById(R.id.productname)
            tvDetail = itemView.findViewById(R.id.productdetail)
            layoutItem = itemView.findViewById(R.id.layoutItem)
        }

    }

    class LoadingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var progressBar: ProgressBar
        init {
            progressBar = itemView.findViewById(R.id.progressbar)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(arrayProduct != null && position == arrayProduct.size - 1 && isLoadingAdd){
            return TYPE_LOADING
        }
            return TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       if(viewType == TYPE_ITEM){
           var view: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_product, null)
           var holder: ProductHolder = ProductHolder(view)
           return holder
       } else{
           var view: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_product, null)
           var loadingView: LoadingViewHolder = LoadingViewHolder(view)
           return loadingView
       }
    }

//    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
//        var item: Product = arrayProduct.get(position)
//        holder.tvName.text = item.name
//        var decimalFormat: DecimalFormat = DecimalFormat("###,###,###");
//        holder.tvPrice.text = decimalFormat.format(item.price)
//        holder.tvDetail.maxLines = 2
//        holder.tvDetail.ellipsize = TextUtils.TruncateAt.END
//        holder.tvDetail.text = item.detail
//        Picasso.get().load(item.image).into(holder.imageView)
//    }

    override fun getItemCount(): Int {
        return arrayProduct.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder.itemViewType == TYPE_ITEM){
            var item: Product = arrayProduct.get(position)
            var productHolder = holder as ProductHolder
            productHolder.tvName.text = item.name
            var decimalFormat: DecimalFormat = DecimalFormat("###,###,###");
            productHolder.tvPrice.text = decimalFormat.format(item.price)
            productHolder.tvDetail.maxLines = 2
            productHolder.tvDetail.ellipsize = TextUtils.TruncateAt.END
            productHolder.tvDetail.text = item.detail
            Picasso.get().load(item.image).into(holder.imageView)
            productHolder.layoutItem.setOnClickListener{
                iClickItemListener.clickItem(item)
            }
        }
    }

    fun addFooterLoading(){
        isLoadingAdd = true
        arrayProduct.add(Product(0, "","","0".toDouble(),"", 0))
    }

    fun removeFooterLoading(){
        isLoadingAdd = false
        var position: Int = arrayProduct.size - 1;
        var product: Product = arrayProduct.get(position);
        if(product!= null){
            arrayProduct.removeAt(position);
            notifyItemRemoved(position)
        }
    }


}