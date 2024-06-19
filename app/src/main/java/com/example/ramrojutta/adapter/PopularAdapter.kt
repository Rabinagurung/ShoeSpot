package com.example.ramrojutta.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.ramrojutta.Model.ItemsModel
import com.example.ramrojutta.activity.DetailActivity
import com.example.ramrojutta.databinding.ViewholderRecommendationBinding
import com.google.gson.Gson

class PopularAdapter(private val popularItems: List<ItemsModel> ):RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {
    class PopularViewHolder(val binding: ViewholderRecommendationBinding ):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularAdapter.PopularViewHolder {
        val binding = ViewholderRecommendationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularAdapter.PopularViewHolder, position: Int) {
        val item = popularItems[position]
        holder.binding.titleTxt.text = item.title
        holder.binding.priceTxt.text = "$${item.price}"
        holder.binding.ratingTxt.text = item.rating.toString()
        val requestOptions = RequestOptions().transform(CenterCrop())
        Glide.with(holder.itemView.context).load(item.picUrl[0]).apply(requestOptions).into(holder.binding.pic)
        holder.itemView.setOnClickListener {
            val intent =  Intent(holder.itemView.context, DetailActivity::class.java )
            val gson = Gson()
            val json = gson.toJson(item)
            Log.d("ItemDebug", json)
            intent.putExtra("object", json)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return popularItems.size
    }
}