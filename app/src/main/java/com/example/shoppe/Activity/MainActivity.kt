package com.example.shoppe.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.shoppe.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showNavigation()
    }

    private fun showNavigation() {
//        setSupportActionBar(toolBar)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        toolBar.setNavigationOnClickListener{
//            drawerLayout.openDrawer(GravityCompat.START)
//        }

        setSupportActionBar(findViewById(R.id.toolBar))
        var toggle : ActionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout,
            findViewById(R.id.toolBar), R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }
}