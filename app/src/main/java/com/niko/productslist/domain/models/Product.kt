package com.niko.productslist.domain.models

data class Product(
    val id : Int,
    val title : String,
    val description : String,
    val price : Int,
    val discountPercentage : Double,
    val rating : Double,
    val stock : Int,
    val brand : String,
    val category : String,
    val thumbnail : String,
    val images : List<String>,
    var isRequired : Boolean
)
