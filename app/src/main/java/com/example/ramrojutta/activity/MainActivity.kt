package com.example.ramrojutta.activity

import MainViewModelFactory
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.ramrojutta.Model.BrandModel
import com.example.ramrojutta.Model.ItemsModel
import com.example.ramrojutta.Model.SliderModel
import com.example.ramrojutta.ViewModel.MainViewModel
import com.example.ramrojutta.adapter.BrandAdapter
import com.example.ramrojutta.adapter.PopularAdapter
import com.example.ramrojutta.adapter.SliderAdapter
import com.example.ramrojutta.databinding.ActivityMainBinding
import com.example.ramrojutta.repository.DataRepository


class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels { MainViewModelFactory(DataRepository()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            viewModel.loadBanners()
            viewModel.loadBrands()
            viewModel.loadPopularItems()
        }
        observeViewModel()
        initBottomMenu()
    }

    private fun initBottomMenu() {
        binding.cartBtn.setOnClickListener() {
            startActivity(Intent(this@MainActivity, CartActivity::class.java))
        }
    }


    private fun observeViewModel() {
        viewModel.banners.observe(this) { banners ->
            updateBanners(banners)
        }

        viewModel.brands.observe(this) { brands ->
            updateBrands(brands)
        }

        viewModel.popularItems.observe(this) {
            updatePopularItems(it)
        }
    }

    private fun updatePopularItems(popularItems: List<ItemsModel>) {
        binding.progressBarPopular.visibility = View.GONE
        binding.viewPopularRV.visibility = View.VISIBLE
        binding.viewPopularRV.adapter = PopularAdapter(popularItems)
        binding.viewPopularRV.layoutManager = GridLayoutManager(this, 2)
    }

    private fun updateBanners(banners: List<SliderModel>) {
        binding.progressBarBanner.visibility = View.GONE
        binding.viewPagerSlider.visibility = View.VISIBLE
        binding.viewPagerSlider.apply {
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
        }
        binding.viewPagerSlider.adapter = SliderAdapter(banners, binding.viewPagerSlider)
        setupPageTransformers()
        binding.dotIndicator.visibility = if (banners.isNotEmpty()) View.VISIBLE else View.GONE
        binding.dotIndicator.attachTo(binding.viewPagerSlider)

    }

    private fun updateBrands(brands: List<BrandModel>) {
        binding.viewBrand.visibility = View.VISIBLE
        binding.viewBrand.adapter = BrandAdapter(brands)
        binding.viewBrand.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.progressBarBrand.visibility = View.GONE
    }

    private fun setupPageTransformers() {
        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }
        binding.viewPagerSlider.setPageTransformer(compositePageTransformer)
    }
}
