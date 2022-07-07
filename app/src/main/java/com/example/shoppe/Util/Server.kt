package com.example.shoppe.Util

object Server {
    val localhost = "192.168.1.30:8012"
    val pathType = "http://"+ localhost +"/shopee/get_type.php";
    val pathNewProduct = "http://"+ localhost +"/shopee/get_newproduct.php";
    val pathProduct = "http://"+ localhost +"/shopee/get_product.php?page=";
}