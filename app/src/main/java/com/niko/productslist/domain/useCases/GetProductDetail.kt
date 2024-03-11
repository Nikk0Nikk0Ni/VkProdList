package com.niko.productslist.domain.useCases

import androidx.lifecycle.LiveData
import com.niko.productslist.domain.models.Product
import com.niko.productslist.domain.repository.ProductRepository

class GetProductDetail(private val repository: ProductRepository) {
    fun getDetail() : LiveData<Product>{
        return repository.getProductDetail()
    }
}