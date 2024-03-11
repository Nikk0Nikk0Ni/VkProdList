package com.niko.productslist.domain.useCases

import androidx.lifecycle.LiveData
import com.niko.productslist.domain.models.Product
import com.niko.productslist.domain.repository.ProductRepository

class GetProductList(private val repository : ProductRepository) {
    fun getProductList() : LiveData<List<Product>>{
        return repository.getProductList()
    }
}