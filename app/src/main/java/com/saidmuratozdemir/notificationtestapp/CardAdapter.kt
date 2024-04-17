package com.saidmuratozdemir.notificationtestapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saidmuratozdemir.notificationtestapp.databinding.ItemHomeCardBinding
import com.saidmuratozdemir.notificationtestapp.listeners.ItemClickListener
import com.saidmuratozdemir.notificationtestapp.model.CardModel


class CardAdapter(var itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<CardAdapter.CostCalculationViewHolder>() {
    lateinit var itemGoalsPreviewBinding: ItemHomeCardBinding
    private var list: List<CardModel> = emptyList()

    class CostCalculationViewHolder(binding: ItemHomeCardBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: ItemHomeCardBinding

        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CostCalculationViewHolder {
        val binding = ItemHomeCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CostCalculationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CostCalculationViewHolder, position: Int) {
        val item = list[position]

        itemGoalsPreviewBinding = holder.binding

        holder.binding.apply {
            itemTitle.text = item.title
            itemDescription.text = item.description
            iteImageView.setImageResource(item.imageRes)
            if (item.shouldShowSwitch) {
                itemSwitch.visibility = ViewGroup.VISIBLE
            } else {
                itemSwitch.visibility = ViewGroup.GONE
            }
        }

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(item)
        }

        holder.itemView.setOnLongClickListener {
            itemClickListener.onLongClick(it, item)
            false
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<CardModel>) {
        this.list = list
        notifyDataSetChanged()
    }

}