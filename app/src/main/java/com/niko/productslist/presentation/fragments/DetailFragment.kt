package com.niko.productslist.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.niko.productslist.R
import com.niko.productslist.databinding.FragmentDetailBinding
import com.niko.productslist.presentation.viewModels.DetailViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DetailFragment : Fragment() {
    private var _binding : FragmentDetailBinding? = null
    private val binding : FragmentDetailBinding
        get() = _binding ?: throw RuntimeException("Detail Fragment == null")
    private val viewModel by lazy {
        ViewModelProvider(this)[DetailViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDetail()
        loadingData()
    }

    private fun loadingData() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            binding.layProgress.visibility = View.GONE
            binding.layInfo.visibility = View.VISIBLE
        }
    }

    private fun setDetail() = with(binding) {
        viewModel.getDetail().observe(viewLifecycleOwner){
            Picasso.get().load(it.thumbnail).into(imgIconDet)
            tvTitle.text = it.title
            tvDesc.text = String.format(resources.getString(R.string.description),it.description)
            tvBrand.text = String.format(resources.getString(R.string.brand),it.brand)
            tvCategory.text = String.format(resources.getString(R.string.category),it.category)
            tvPrice.text = String.format(resources.getString(R.string.price),it.price)
            tvDiscPerc.text = String.format(resources.getString(R.string.discount_percentage),it.discountPercentage)
            tvRaiting.text = String.format(resources.getString(R.string.raiting),it.rating)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}