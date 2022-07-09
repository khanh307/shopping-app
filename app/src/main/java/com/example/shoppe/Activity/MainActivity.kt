package com.example.shoppe.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.shoppe.Adapter.ListViewNavigationAdapter
import com.example.shoppe.Data.NavigationItem
import com.example.shoppe.Data.Product
import com.example.shoppe.R
import com.example.shoppe.Util.CheckConnection
import com.example.shoppe.Util.Server
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    var arrayItem : ArrayList<NavigationItem> = ArrayList()
    var adapter: ListViewNavigationAdapter = ListViewNavigationAdapter(this, arrayItem)
    var arrayNewProduct: ArrayList<Product> = ArrayList()
    var homeFragment: Home_Fragment = Home_Fragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(homeFragment)

        showNavigation()

        if (CheckConnection.haveNetworkConnection(applicationContext)){
            setAdapterListViewNavigation()

        } else{
            CheckConnection.showToast(applicationContext, "Kiểm tra kết nối")
        }

    }

    fun intentActivity(product: Product){
        var mIntent: Intent = Intent(this, ProductDetail::class.java)
        var bundle: Bundle = Bundle()
        bundle.putSerializable("object_product", product)
        mIntent.putExtras(bundle)
        startActivity(mIntent)
        overridePendingTransition(R.anim.slide_enter_left, R.anim.slide_exit_left)
    }


    private fun setAdapterListViewNavigation() {
        getDataNavigation()
        listview_navigation.adapter = adapter
        listview_navigation.setOnItemClickListener { parent, view, position, id ->
            view.isSelected = true
            var index = id.toString()
            if(index.equals("0")){
                replaceFragment(homeFragment)
                homeFragment.arrayNewProduct = arrayNewProduct
                drawerLayout.closeDrawer(GravityCompat.START)
            } else if(index.equals("1")){
                var productFragment: ProductFragment = ProductFragment()
                var bundle: Bundle = Bundle()
                bundle.putString("id", "1")
                productFragment.arguments = bundle
                replaceFragment(productFragment)
                productFragment.arrayProduct = arrayNewProduct
                drawerLayout.closeDrawer(GravityCompat.START)
            } else if(index.equals("2")){
                var productFragment: ProductFragment = ProductFragment()
                var bundle: Bundle = Bundle()
                bundle.putString("id", "2")
                productFragment.arguments = bundle
                replaceFragment(productFragment)
                drawerLayout.closeDrawer(GravityCompat.START)
            } else if(index.equals("3")){
                var productFragment: ProductFragment = ProductFragment()
                var bundle: Bundle = Bundle()
                bundle.putString("id", "3")
                productFragment.arguments = bundle
                replaceFragment(productFragment)
                drawerLayout.closeDrawer(GravityCompat.START)
            } else if(index.equals("4")){
                var productFragment: ProductFragment = ProductFragment()
                var bundle: Bundle = Bundle()
                bundle.putString("id", "4")
                productFragment.arguments = bundle
                replaceFragment(productFragment)
                drawerLayout.closeDrawer(GravityCompat.START)
            } else if(index.equals("5")){
                var productFragment: ProductFragment = ProductFragment()
                var bundle: Bundle = Bundle()
                bundle.putString("id", "5")
                productFragment.arguments = bundle
                replaceFragment(productFragment)
                drawerLayout.closeDrawer(GravityCompat.START)
            } else if(index.equals("6")){
                var productFragment: ProductFragment = ProductFragment()
                var bundle: Bundle = Bundle()
                bundle.putString("id", "6")
                productFragment.arguments = bundle
                replaceFragment(productFragment)
                drawerLayout.closeDrawer(GravityCompat.START)
            }
        }
        adapter.notifyDataSetInvalidated()
    }

    fun getDataNavigation(){
        var requestQueue: RequestQueue = Volley.newRequestQueue(applicationContext);
        var jsonArray: JsonArrayRequest = JsonArrayRequest(Server.pathType, Response.Listener {response ->
            if (response != null){
                for(i in 0 until response.length()){
                    var jsonObject: JSONObject = response.getJSONObject(i)
                    var id = jsonObject.getInt("id")
                    var name = jsonObject.getString("name")
                    var icon = jsonObject.getString("icon")
                    icon = icon.replace("localhost:8012", Server.localhost)
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
        tranction.setCustomAnimations(R.anim.open_fragment, R.anim.exit_fragment)
        tranction.replace(R.id.content_frame,fragment)
        tranction.commit()
    }



}