package com.niko.productslist.presentation.recView

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.niko.productslist.R
import com.niko.productslist.domain.models.Product

class ProductAdapter(private val context: Context) :
    ListAdapter<Product, ProductHolder>(ProductComporator()) {
    var onClick: ((Product) -> Unit)? = null
    var onLongTap: ((Product) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.product_item,
            parent,
            false
        )
        return ProductHolder(view)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.bind(getItem(position), context, onClick, onLongTap)
    }
}