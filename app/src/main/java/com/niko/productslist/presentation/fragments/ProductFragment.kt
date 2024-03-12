package com.niko.productslist.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.niko.productslist.R
import com.niko.productslist.data.repository.ProductRepositoryImpl
import com.niko.productslist.databinding.FragmentProductBinding
import com.niko.productslist.domain.useCases.GetProductList
import com.niko.productslist.presentation.recView.ProductAdapter
import com.niko.productslist.presentation.viewModels.ProductViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean

class ProductFragment : Fragment() {
    private var _binding: FragmentProductBinding? = null
    private val binding: FragmentProductBinding
        get() = _binding ?: throw RuntimeException("Product fragment == null")
    private val adapter by lazy {
        ProductAdapter(requireContext())
    }
    private val viewModel by lazy {
        ViewModelProvider(this)[ProductViewModel::class.java]
    }

    private fun observeProductList() {
        viewModel.getProductList().observe(viewLifecycleOwner) {
            adapter.submitList(it.toList())
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        observeProductList()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecView()
    }

    private fun initRecView() {
        binding.prodRecView.adapter = adapter
        adapter.onClick = {
            findNavController().navigate(R.id.action_productFragment_to_detailFragment)
            viewModel.setDetail(it.id)
        }
        adapter.onLongTap = {
            if (!it.isRequired)
                viewModel.addToBacket(it.id)
            else
                viewModel.removeFromBacket(it.id)
            adapter.notifyItemChanged(it.id - 1)
        }
        addPagination()
    }

    private fun addPagination() {
        binding.prodRecView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount - 5) {
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.getUsersWithPagination()
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}