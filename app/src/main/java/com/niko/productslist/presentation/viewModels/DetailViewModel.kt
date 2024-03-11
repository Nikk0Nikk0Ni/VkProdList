package com.niko.productslist.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.niko.productslist.data.repository.ProductRepositoryImpl
import com.niko.productslist.domain.models.Product
import com.niko.productslist.domain.useCases.GetProductDetail

class DetailViewModel : ViewModel() {
    private val reporitory = ProductRepositoryImpl
    private val getProductDetail = GetProductDetail(reporitory)

    fun getDetail(): LiveData<Product> {
        return getProductDetail.getDetail()
    }
}