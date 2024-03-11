package com.niko.productslist.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.niko.productslist.R
import com.niko.productslist.databinding.FragmentBacketBinding
import com.niko.productslist.presentation.recView.ProductAdapter
import com.niko.productslist.presentation.viewModels.BacketViewModel
import java.lang.RuntimeException

class BacketFragment : Fragment() {
    private var _binding: FragmentBacketBinding? = null
    private val binding: FragmentBacketBinding
        get() = _binding ?: throw RuntimeException("Fragment game binding == null")
    private val viewModel by lazy {
        ViewModelProvider(this)[BacketViewModel::class.java]
    }

    private lateinit var adapter : ProductAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBacketBinding.inflate(inflater, container, false)
        observeBacketList()
        return binding.root
    }

    private fun observeBacketList() {
        viewModel.getBacketList().observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecView()

    }

    private fun initRecView() {
        adapter = ProductAdapter(requireContext())
        binding.backetRecView.adapter = adapter
        adapter.onClick = {
            viewModel.setDetail(it.id)
            findNavController().navigate(R.id.action_backetFragment_to_detailFragment)
        }
        adapter.onLongTap = {
            viewModel.removeFromBacket(it.id)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}