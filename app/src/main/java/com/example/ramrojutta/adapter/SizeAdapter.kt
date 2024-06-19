package com.example.ramrojutta.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ramrojutta.R
import com.example.ramrojutta.databinding.ViewholderSizeBinding

class SizeAdapter(private val items: List<String>):RecyclerView.Adapter<SizeAdapter.SizeViewHolder>() {
    private var selectedPosition = -1

    inner class SizeViewHolder(val binding: ViewholderSizeBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SizeViewHolder {
        val binding = ViewholderSizeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SizeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SizeViewHolder, position: Int) {
        val item = items[position]
        updateContent(holder, item)
        setupClickListener(holder)
        updateSelectionState(holder)

    }

    private fun updateContent(holder: SizeViewHolder, item: String) {
        holder.binding.sizeTxt.text = item
    }

    private fun setupClickListener(holder: SizeViewHolder) {
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

    private fun updateSelectionState(holder: SizeViewHolder) {
        if(selectedPosition == holder.adapterPosition) {
            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg_selected)
            holder.binding.sizeTxt.setTextColor(holder.itemView.context.getColor(R.color.purple))
        } else {
            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg)
            holder.binding.sizeTxt.setTextColor(holder.itemView.context.getColor(R.color.black))
        }
    }

    override fun getItemCount(): Int = items.size

}