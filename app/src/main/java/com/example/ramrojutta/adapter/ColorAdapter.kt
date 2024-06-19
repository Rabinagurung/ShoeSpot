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
import com.example.ramrojutta.databinding.ViewholderColorBinding

class ColorAdapter(private val items: List<String>):RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {
    private var selectedPosition = -1

    class ColorViewHolder(val binding: ViewholderColorBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ColorViewHolder {
        val binding = ViewholderColorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ColorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val item = items[position]
        updateContent(holder, item)
        setupClickListener(holder)
        updateSelectionState(holder)

    }

    private fun updateContent(holder: ColorViewHolder, item: String) {
        Glide.with(holder.itemView.context).load(item).into(holder.binding.pic)
    }

    private fun setupClickListener(holder: ColorViewHolder) {
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

    private fun updateSelectionState(holder: ColorViewHolder) {
        if(selectedPosition == holder.adapterPosition) {
            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg_selected)
        } else {
            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg)
        }
    }

    override fun getItemCount(): Int = items.size

}