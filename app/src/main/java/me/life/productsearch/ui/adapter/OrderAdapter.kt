package me.life.productsearch.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.life.productsearch.databinding.ListItemOrdersBinding
import me.life.productsearch.model.OrderDataItem

class OrderAdapter : ListAdapter<OrderDataItem, OrderAdapter.ViewHolder>(DataDiffCallBack()) {

    private class DataDiffCallBack : DiffUtil.ItemCallback<OrderDataItem>() {
        override fun areItemsTheSame(oldItem: OrderDataItem, newItem: OrderDataItem): Boolean =
            oldItem.order_number == newItem.order_number

        override fun areContentsTheSame(oldItem: OrderDataItem, newItem: OrderDataItem): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemOrdersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ListItemOrdersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: OrderDataItem) {
            binding.tvTitle.text = item.order_number
            binding.tvPrice.text = item.status_label
        }
    }
}