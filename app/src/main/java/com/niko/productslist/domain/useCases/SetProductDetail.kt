package com.niko.productslist.domain.useCases

import com.niko.productslist.domain.repository.ProductRepository

class SetProductDetail(private val repository: ProductRepository) {
    fun setDetail(id : Int){
        repository.setProductDetail(id)
    }
}