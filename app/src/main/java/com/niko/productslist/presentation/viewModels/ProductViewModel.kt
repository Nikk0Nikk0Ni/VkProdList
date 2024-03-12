package com.niko.productslist.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.niko.productslist.data.repository.ProductRepositoryImpl
import com.niko.productslist.domain.models.Product
import com.niko.productslist.domain.useCases.AddToBacket
import com.niko.productslist.domain.useCases.GetProductList
import com.niko.productslist.domain.useCases.GetUsersWithPagination
import com.niko.productslist.domain.useCases.SetProductDetail

class ProductViewModel : ViewModel() {
    private val repository = ProductRepositoryImpl
    private val getList = GetProductList(repository)
    private val addToBacket = AddToBacket(repository)
    private val setDetail = SetProductDetail(repository)
    private val getUsers = GetUsersWithPagination(repository)

    fun getProductList() : LiveData<List<Product>>{
        return getList.getProductList()
    }
    fun addToBacket(id : Int){
        addToBacket.addToBacket(id)
    }
    fun setDetail(id: Int){
        setDetail.setDetail(id)
    }

    fun getUsersWithPagination(){
        getUsers.getUsersWithPagination()
    }

}