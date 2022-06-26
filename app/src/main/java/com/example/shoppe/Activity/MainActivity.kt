package com.example.shoppe.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.shoppe.Adapter.ListViewNavigationAdapter
import com.example.shoppe.Data.NavigationItem
import com.example.shoppe.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var arrayItem : ArrayList<NavigationItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showNavigation()

        setAdapterListViewNavigation()

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