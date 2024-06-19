package com.example.ramrojutta.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.ramrojutta.Model.SliderModel
import com.example.ramrojutta.databinding.SliderItemContainerBinding

class SliderAdapter(private var sliderItems: List<SliderModel> = listOf(), private val viewPager2: ViewPager2):RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    private val runnable = Runnable {
        sliderItems = sliderItems
        notifyDataSetChanged()
    }
    class SliderViewHolder(val binding: SliderItemContainerBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderAdapter.SliderViewHolder {
        val binding = SliderItemContainerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SliderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderAdapter.SliderViewHolder, position: Int) {

        val item = sliderItems[position]
//        var requestOptions = RequestOptions()
//        requestOptions = requestOptions.transform(CenterCrop(), RoundedCorners(60))
        Glide.with(holder.itemView.context)
            .load(item.url)
//            .apply(requestOptions)
            .into(holder.binding.imageSlider)

        if(position == sliderItems.lastIndex - 1) {
            viewPager2.post(runnable)
        }
    }

    override fun getItemCount(): Int {
        return sliderItems.size
    }
}