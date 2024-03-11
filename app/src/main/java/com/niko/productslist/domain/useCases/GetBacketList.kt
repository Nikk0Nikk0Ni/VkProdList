package com.niko.productslist.domain.useCases

import androidx.lifecycle.LiveData
import com.niko.productslist.domain.models.Product
import com.niko.productslist.domain.repository.ProductRepository

class GetBacketList(private val repository : ProductRepository) {
    fun getBacketList() : LiveData<List<Product>>{
        return repository.getBacketList()
    }
}