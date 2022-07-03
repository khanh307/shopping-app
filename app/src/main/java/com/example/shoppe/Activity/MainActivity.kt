package com.example.shoppe.Activity

import android.R.attr.bitmap
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.shoppe.Adapter.ListViewNavigationAdapter
import com.example.shoppe.Adapter.ProductAdapter
import com.example.shoppe.Data.NavigationItem
import com.example.shoppe.Data.Product
import com.example.shoppe.R
import com.example.shoppe.Util.CheckConnection
import com.example.shoppe.Util.Server
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.net.URL


class MainActivity : AppCompatActivity() {

    var arrayItem : ArrayList<NavigationItem> = ArrayList()
    var adapter: ListViewNavigationAdapter = ListViewNavigationAdapter(this, arrayItem)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(Home_Fragment())
        showNavigation()

        if (CheckConnection.haveNetworkConnection(applicationContext)){
            setAdapterListViewNavigation()
        } else{
            CheckConnection.showToast(applicationContext, "Kiểm tra kết nối")
        }

    }
    var frame = ProductFragment()
    private fun setAdapterListViewNavigation() {
        getDataNavigation()
        listview_navigation.adapter = adapter

        listview_navigation.setOnItemClickListener { parent, view, position, id ->
            view.isSelected = true
            var index = id.toString()
            if(index.equals("0")){
                replaceFragment(Home_Fragment())
                Toast.makeText(applicationContext, id.toString(), Toast.LENGTH_SHORT).show()
                drawerLayout.closeDrawer(GravityCompat.START)
            } else if(index.equals("1")){

                var bundle: Bundle = Bundle()
                bundle.putString("title","Điện thoại")
                frame.arguments = bundle
                replaceFragment(frame)
                drawerLayout.closeDrawer(GravityCompat.START)
            } else if(index.equals("2")){
                var bundle: Bundle = Bundle()
                bundle.putString("title","Sạc dự phòng")
                frame.arguments = bundle
                replaceFragment(frame)
                drawerLayout.closeDrawer(GravityCompat.START)
            } else if(index.equals("3")){
                var bundle: Bundle = Bundle()
                bundle.putString("title","Tai nghe")
                frame.arguments = bundle
                replaceFragment(frame)
                drawerLayout.closeDrawer(GravityCompat.START)
            } else if(index.equals("4")){
                var bundle: Bundle = Bundle()
                bundle.putString("title","Ốp lưng")
                frame.arguments = bundle
                replaceFragment(frame)
                drawerLayout.closeDrawer(GravityCompat.START)
            } else if(index.equals("5")){
                var bundle: Bundle = Bundle()
                bundle.putString("title","Cáp sạc")
                frame.arguments = bundle
                replaceFragment(frame)
                drawerLayout.closeDrawer(GravityCompat.START)
            } else if(index.equals("6")){
                var bundle: Bundle = Bundle()
                bundle.putString("title","Loa")
                frame.arguments = bundle
                replaceFragment(frame)
                drawerLayout.closeDrawer(GravityCompat.START)
            }
        }
        adapter.notifyDataSetInvalidated()
    }

    fun getDataNavigation(){
        var requestQueue: RequestQueue = Volley.newRequestQueue(applicationContext);
        var jsonArray: JsonArrayRequest = JsonArrayRequest(Server.pathProduct, Response.Listener {response ->
            if (response != null){
                for(i in 0 until response.length()){
                    var jsonObject: JSONObject = response.getJSONObject(i)
                    var id = jsonObject.getInt("id")
                    var name = jsonObject.getString("name")
                    var icon = jsonObject.getString("icon")
                    icon = icon.replace("localhost:8012", Server.localhost)
                    Log.d("CCC", icon)
                    arrayItem.add(NavigationItem(id, icon, name))
                    adapter.notifyDataSetInvalidated()
                }
            }
        }, Response.ErrorListener {

        })
        requestQueue.add(jsonArray)
    }



    private fun showNavigation() {
        setSupportActionBar(findViewById(R.id.toolBar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolBar.setNavigationOnClickListener{
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    private fun replaceFragment(fragment: Fragment){
        var tranction: FragmentTransaction = supportFragmentManager.beginTransaction()
        tranction.replace(R.id.content_frame,fragment)
        tranction.commit()
    }

    private fun setApdapterProduct(){

    }

}