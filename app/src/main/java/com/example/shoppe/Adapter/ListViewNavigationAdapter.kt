package com.example.shoppe.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.shoppe.Data.NavigationItem
import com.example.shoppe.R
import com.squareup.picasso.Picasso

class ListViewNavigationAdapter(var context: Context, var arrayList: ArrayList<NavigationItem>): BaseAdapter() {
    override fun getCount(): Int {
       return arrayList.size
    }

    override fun getItem(position: Int): Any {
        return arrayList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    private class ViewHolder(row : View){
        var imageItem: ImageView
        var nameItem: TextView

        init {
            imageItem = row.findViewById(R.id.image_item)
            nameItem = row.findViewById(R.id.name_item)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view : View?
        var viewHolder : ViewHolder
        if(convertView == null){
            var layoutFlater : LayoutInflater = LayoutInflater.from(context)
            view = layoutFlater.inflate(R.layout.item_navigation, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }else{
            view = convertView;
            viewHolder = convertView.tag as ViewHolder
        }
        var line : NavigationItem = getItem(position) as NavigationItem
        viewHolder.nameItem.text = line.name
        Picasso.get().load(line.image)
            .error(R.drawable.ic_menu)
            .into(viewHolder.imageItem)
        return view as View
    }
}