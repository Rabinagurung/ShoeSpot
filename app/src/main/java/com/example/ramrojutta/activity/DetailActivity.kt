package com.example.ramrojutta.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.ramrojutta.Model.ItemsModel
import com.example.ramrojutta.Model.SliderModel
import com.example.ramrojutta.adapter.ColorAdapter
import com.example.ramrojutta.adapter.SizeAdapter
import com.example.ramrojutta.adapter.SliderAdapter
import com.example.ramrojutta.databinding.ActivityDetailBinding
import com.example.ramrojutta.helper.ManagmentCart
import com.google.gson.Gson

class DetailActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item: ItemsModel
    private var numberOrder = 1
    private lateinit var managmentCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        managmentCart = ManagmentCart(this)
        getData()
        banners()
        initLists()
    }

    private fun initLists() {
        val sizeList = ArrayList<String>()
        for (size in item.size) {
            sizeList.add(size.toString())
        }
        binding.sizeList.adapter = SizeAdapter(sizeList)
        binding.sizeList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val colorList = ArrayList<String>()
        for (imageUrl in item.picUrl) {
            colorList.add(imageUrl)
        }
        binding.colorListRV.adapter = ColorAdapter(colorList)
        binding.colorListRV.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun banners() {
        val sliderItems = ArrayList<SliderModel>()
        for (imageUrl in item.picUrl) {
            sliderItems.add(SliderModel(imageUrl))

        }
        binding.slider.apply {
            clipToPadding = true
            clipChildren = true
            offscreenPageLimit = 1
        }
        binding.slider.adapter = SliderAdapter(sliderItems, binding.slider)
        setupPageTransformers()
        binding.dotIndicator.visibility = if (sliderItems.isNotEmpty()) View.VISIBLE else View.GONE
        binding.dotIndicator.attachTo(binding.slider)
    }

    private fun setupPageTransformers() {
        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }
        binding.slider.setPageTransformer(compositePageTransformer)
    }

    private fun getData() {
        val jsonString = intent.getStringExtra("object")
        jsonString?.let {
            val gson = Gson()
            item = gson.fromJson(it, ItemsModel::class.java)
            print(item.title)
            item.let {
                binding.titleTxt.text = it.title
                binding.descriptionTxt.text = it.description
                binding.priceTxt.text = "$${it.price}"
                binding.ratingTxt.text = "${it.rating} Rating"
                binding.addToCartBtn.setOnClickListener() { _ ->
                    it.numberInCart = numberOrder
                    managmentCart.insertFood(it)
                }

            }

        }
        binding.backBtn.setOnClickListener {
            finish()
        }
        binding.cartBtn.setOnClickListener {
            // Handle cart button click
//                    Toast.makeText(this, "Item added to card", Toast.LENGTH_SHORT)
            startActivity(Intent(this@DetailActivity, CartActivity::class.java))
        }
    }
}