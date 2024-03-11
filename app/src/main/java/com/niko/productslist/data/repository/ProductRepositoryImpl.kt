package com.niko.productslist.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.niko.productslist.data.api.api
import com.niko.productslist.domain.models.Product
import com.niko.productslist.domain.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
const val limit = 20
object ProductRepositoryImpl : ProductRepository {
    private var skip = 0
    private var productList = listOf<Product>()

    private val productListLD = MutableLiveData<List<Product>>()
    private val bucketListLD = MutableLiveData<List<Product>>()
    private val productDetail = MutableLiveData<Product>()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val api_list = async { api.getProductList(skip,limit).products }
            productList = api_list.await()
            getProductList()
        }
    }

    override fun getProductList(): LiveData<List<Product>> {
        productListLD.postValue(productList)
        return productListLD
    }

    override fun getBacketList(): LiveData<List<Product>> {
        bucketListLD.postValue(productList.filter { it.isRequired })
        return bucketListLD
    }

    override fun getProductDetail(): LiveData<Product> {
        return productDetail
    }

    override fun setProductDetail(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val detail = async { api.getProductDetail(id) }
            productDetail.postValue(detail.await())
        }
    }

    override fun addToBacket(id: Int) {
        productList.find { it.id == id }?.isRequired = true
        getBacketList()
    }

    override fun removeFromBacket(id: Int) {
        productList.find { it.id == id }?.isRequired = false
        getBacketList()
    }
}