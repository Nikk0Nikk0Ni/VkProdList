package com.niko.productslist.presentation.recView

import androidx.recyclerview.widget.DiffUtil
import com.niko.productslist.domain.models.Product

class ProductComporator : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }

}