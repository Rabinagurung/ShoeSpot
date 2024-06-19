package com.example.ramrojutta.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ramrojutta.Model.ItemsModel
import com.example.ramrojutta.databinding.ViewholderCartBinding
import com.example.ramrojutta.helper.ChangeNumberItemsListener
import com.example.ramrojutta.helper.ManagmentCart

class CartAdapter(
    private val listItemSelected: ArrayList<ItemsModel>,
    context: Context,
    var changeNumberItemsListener: ChangeNumberItemsListener? = null,
):RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(val binding: ViewholderCartBinding):RecyclerView.ViewHolder(binding.root)

    private val managmentCart = ManagmentCart(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = listItemSelected[position]
        holder.binding.titleTxt.text = item.title
        holder.binding.feeEachItem.text = "$${item.price}"
        holder.binding.total.text = "$${Math.round(item.numberInCart * item.price)}"

        holder.binding.numberItemTxt.text = "${item.numberInCart}"

        Glide.with(holder.itemView.context).load(item.picUrl[0]).into(holder.binding.pic)
        holder.binding.plusCardBtn.setOnClickListener {
            managmentCart.plusItem(listItemSelected, position, object: ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }

            })

        }
        holder.binding.minusCardBtn.setOnClickListener {
            managmentCart.minusItem(listItemSelected, position, object: ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }

            })
        }

    }

    override fun getItemCount() = listItemSelected.size


}