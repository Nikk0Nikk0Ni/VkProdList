package com.niko.productslist.data.api

import com.niko.productslist.domain.models.Product
import com.niko.productslist.domain.models.ProductList
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsApi {
    @GET("products")
    fun getProductList(@Query("skip") skip : Int, @Query("limit") limit : Int): Call<ProductList>

    @GET("products/{id}")
    suspend fun getProductDetail(@Path("id") id : Int): Product
}

private val retrofit = Retrofit.Builder()
    .baseUrl("https://dummyjson.com")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
val api = retrofit.create(ProductsApi::class.java)