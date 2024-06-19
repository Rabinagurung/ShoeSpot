package com.example.ramrojutta.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ramrojutta.Model.BrandModel
import com.example.ramrojutta.R
import com.example.ramrojutta.databinding.ViewholderBrandBinding

class BrandAdapter(private val items: List<BrandModel>):RecyclerView.Adapter<BrandAdapter.BrandViewHolder>() {
    private var selectedPosition = -1

    class BrandViewHolder(val binding: ViewholderBrandBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BrandViewHolder {
        val binding = ViewholderBrandBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BrandViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        val item = items[position]
        updateContent(holder, item)
        setupClickListener(holder)
        updateSelectionState(holder)
    }

    private fun updateContent(holder: BrandViewHolder, item: BrandModel) {
        holder.binding.titleTxt.text = item.title
        Glide.with(holder.itemView.context).load(item.picUrl).into(holder.binding.pic)
    }

    private fun setupClickListener(holder: BrandViewHolder) {
        holder.binding.root.setOnClickListener {
            val newPosition = holder.adapterPosition
            if (newPosition != RecyclerView.NO_POSITION) {
                val oldPosition = selectedPosition
                selectedPosition = newPosition
                notifyItemChanged(oldPosition)
                notifyItemChanged(newPosition)
            }
        }
    }

    private fun updateSelectionState(holder: BrandAdapter.BrandViewHolder) {
        val context = holder.itemView.context
        if(selectedPosition == holder.adapterPosition) {
            holder.binding.pic.setBackgroundResource(0)
            holder.binding.mainLayout.setBackgroundResource(R.drawable.purple_bg)
            ImageViewCompat.setImageTintList(holder.binding.pic, ColorStateList.valueOf(context.resources.getColor(R.color.white, null)))
            holder.binding.titleTxt.visibility = View.VISIBLE
            holder.binding.titleTxt.setTextColor(context.getColor(R.color.white))
        } else {
            holder.binding.pic.setBackgroundResource(R.drawable.grey_bg)
            holder.binding.mainLayout.setBackgroundResource(0)
            ImageViewCompat.setImageTintList(holder.binding.pic, ColorStateList.valueOf(context.resources.getColor(R.color.black, null)))
            holder.binding.titleTxt.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = items.size

}