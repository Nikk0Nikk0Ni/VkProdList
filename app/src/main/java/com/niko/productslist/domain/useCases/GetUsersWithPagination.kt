package com.niko.productslist.domain.useCases

import com.niko.productslist.domain.repository.ProductRepository

class GetUsersWithPagination(private val repository: ProductRepository) {
    fun getUsersWithPagination(){
        repository.getUsersWithPagination()
    }
}