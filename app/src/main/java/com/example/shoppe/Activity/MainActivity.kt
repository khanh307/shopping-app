package com.example.shoppe.Activity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.shoppe.Adapter.ListViewNavigationAdapter
import com.example.shoppe.Data.NavigationItem
import com.example.shoppe.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL

class MainActivity : AppCompatActivity() {

    var arrayItem : ArrayList<NavigationItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showNavigation()

        setAdapterListViewNavigation()


        var url = "http://192.168.1.172:8012/shopee/images/ic_app.png"
        var srl = "https://cdn01.dienmaycholon.vn/filewebdmclnew/public//userupload/images/he-dieu-hanh-android-la-gi-3.jpg"
        Picasso.get().load(url)
            .error(R.drawable.ic_battery)
            .into(imageview)

    }

    private fun setAdapterListViewNavigation() {
        arrayItem.add(NavigationItem(R.drawable.ic_home, "Home"))
        arrayItem.add(NavigationItem(R.drawable.ic_smartphone, "Smartphone"))
        arrayItem.add(NavigationItem(R.drawable.ic_battery, "Battery"))
        arrayItem.add(NavigationItem(R.drawable.ic_headphones, "Headphone"))
        var adapter: ListViewNavigationAdapter = ListViewNavigationAdapter(this, arrayItem)
        listview_navigation.adapter = adapter
    }

    private fun showNavigation() {
        setSupportActionBar(findViewById(R.id.toolBar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolBar.setNavigationOnClickListener{
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

}