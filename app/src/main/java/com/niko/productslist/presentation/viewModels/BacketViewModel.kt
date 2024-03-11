package com.niko.productslist.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.niko.productslist.data.repository.ProductRepositoryImpl
import com.niko.productslist.domain.models.Product
import com.niko.productslist.domain.useCases.GetBacketList
import com.niko.productslist.domain.useCases.RemoveFromBacket
import com.niko.productslist.domain.useCases.SetProductDetail

class BacketViewModel : ViewModel() {
    private val repository = ProductRepositoryImpl
    private val getBacketList = GetBacketList(repository)
    private val removeFromBacket = RemoveFromBacket(repository)
    private val setDetail = SetProductDetail(repository)

    fun getBacketList(): LiveData<List<Product>> {
        return getBacketList.getBacketList()
    }
    fun removeFromBacket(id : Int){
        removeFromBacket.removeFromBacket(id)
    }
    fun setDetail(id: Int){
        setDetail.setDetail(id)
    }

}