package com.salim.foodmarket.ui.order.pastorders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.salim.foodmarket.R
import com.salim.foodmarket.model.response.transaction.Data
import com.salim.foodmarket.utils.Helpers.convertLongToTime
import com.salim.foodmarket.utils.Helpers.formatPrice
import kotlinx.android.synthetic.main.item_pastorders.view.*

class PastordersAdapter(
        private val listData: List<Data>,
        private val itemAdapterCallback: ItemAdapterCallback

) : RecyclerView.Adapter<PastordersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_pastorders, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data : Data, itemAdapterCallback : ItemAdapterCallback) {
            itemView.apply {
                tvTitle.text = data.food.name
                tvPrice.formatPrice(data.food.price.toString())
                tvDate.text = data.food.createdAt.convertLongToTime("MM dd , HH.mm")
                Glide.with(context)
                    .load(data.food.picturePath)
                    .into(ivPoster)
                if (data.status.equals("CANCELLED", true)) {
                    tvCancelled.visibility = View.VISIBLE
                }
                itemView.setOnClickListener { view -> itemAdapterCallback.onClick(view, data) }
            }
        }
    }

    interface ItemAdapterCallback {
        fun onClick(v: View?, data:Data)
    }
}