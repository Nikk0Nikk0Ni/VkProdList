package com.niko.productslist.domain.useCases

import com.niko.productslist.domain.repository.ProductRepository

class RemoveFromBacket(private val repository: ProductRepository) {
    fun removeFromBacket(id : Int){
        repository.removeFromBacket(id)
    }
}