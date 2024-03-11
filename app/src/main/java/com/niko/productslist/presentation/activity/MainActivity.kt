package com.niko.productslist.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.niko.productslist.R
import com.niko.productslist.data.repository.ProductRepositoryImpl
import com.niko.productslist.databinding.ActivityMainBinding
import com.niko.productslist.domain.useCases.GetProductDetail
import com.niko.productslist.domain.useCases.GetProductList
import com.niko.productslist.presentation.fragments.BacketFragment
import com.niko.productslist.presentation.fragments.ProductFragment
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw RuntimeException("Main Activity == null")
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        navController = findNavController(binding.fragmentContainerView.id)
        initBtns()
    }

    private fun initBtns() = with(binding) {
        products.setOnClickListener {
            if (navController.currentDestination?.id != R.id.productFragment)
                navController.navigate(R.id.productFragment)
        }
        backet.setOnClickListener {
            if (navController.currentDestination?.id != R.id.backetFragment)
                navController.navigate(R.id.backetFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}