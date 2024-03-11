package com.niko.productslist.domain.repository

import androidx.lifecycle.LiveData
import com.niko.productslist.domain.models.Product

interface ProductRepository {

    fun getProductList(): LiveData<List<Product>>

    fun getBacketList(): LiveData<List<Product>>

    fun getProductDetail(): LiveData<Product>

    fun setProductDetail(id: Int)

    fun addToBacket(id: Int)

    fun removeFromBacket(id: Int)

}