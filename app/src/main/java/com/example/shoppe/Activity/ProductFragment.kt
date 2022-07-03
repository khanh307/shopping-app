package com.example.shoppe.Activity

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.shoppe.R

class ProductFragment : Fragment() {
    lateinit var textview: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_product, container, false)
        textview = view.findViewById(R.id.textview)
        Log.d("CCC", "onCreateView")
        return view
    }

    override fun onStart() {
        super.onStart()
        Log.d("CCC", "onStart")
        var bundle = arguments
        if(bundle != null){
            textview.text = bundle.getString("title")
        }
    }


}