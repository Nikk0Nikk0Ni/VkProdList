package com.niko.productslist.domain.useCases

import com.niko.productslist.domain.repository.ProductRepository

class AddToBacket(private val repository : ProductRepository) {
    fun addToBacket(id : Int){
        repository.addToBacket(id)
    }
}