package com.example.shoppe.Util

object Server {
    val localhost = "diamantiferous-tugs.000webhostapp.com"
//    val localhost = "192.168.1.3:8012"
    val pathType = "http://"+ localhost +"/shopee/get_type.php";
    val pathNewProduct = "http://"+ localhost +"/shopee/get_newproduct.php";
    val pathProduct = "http://"+ localhost +"/shopee/get_product.php?page=";
    val pathCart = "http://"+ localhost + "/shopee/add_to_cart.php";
    val pathUpdateCart = "http://"+ localhost + "/shopee/update_cart.php";
}