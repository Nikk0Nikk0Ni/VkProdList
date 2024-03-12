package com.niko.productslist.presentation.recView

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.niko.productslist.R
import com.niko.productslist.databinding.ProductItemBinding
import com.niko.productslist.domain.models.Product
import com.squareup.picasso.Picasso

class ProductHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ProductItemBinding.bind(view)
    fun bind(product: Product, context: Context, onClick: ((Product) -> Unit)?, onLong : ((Product)->Unit)?) =
        with(binding) {
            Picasso.get().load(product.thumbnail).into(imgIcon)
            val title = if (product.title.length > 24)
                product.title.substring(0..22) + "..."
            else
                product.title
            tvTitle.text = title
            tvPrice.text = String.format(
                context.getString(R.string.price), product.price
            )
            val desc =
                if (product.description.length > 44)
                    product.description.substring(0..35) + "..."
                else
                    product.description
            tvDesctiption.text = String.format(
                context.getString(R.string.description), desc
            )
            itemView.setOnClickListener {
                onClick?.invoke(product)
            }
            itemView.setOnLongClickListener{
                onLong?.invoke(product)
                true
            }
            if(product.isRequired)
                binding.imgLike.visibility = View.VISIBLE
            else
                binding.imgLike.visibility = View.GONE
        }
}