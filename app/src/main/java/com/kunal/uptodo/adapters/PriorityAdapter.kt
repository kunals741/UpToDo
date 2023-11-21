package com.kunal.uptodo.adapters

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kunal.uptodo.R
import com.kunal.uptodo.databinding.ItemPriorityBinding

class PriorityAdapter(
    private val k: Int,
    private val onItemClick: ((Int) -> Unit)
) : RecyclerView.Adapter<PriorityAdapter.PriorityAdapterViewHolder>() {
    private lateinit var binding: ItemPriorityBinding
    private var lastPosition = RecyclerView.NO_POSITION
    private var selectedPosition = 0

    inner class PriorityAdapterViewHolder(
        private val binding: ItemPriorityBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(priorityNumber: Int, onItemClick: ((Int) -> Unit)) {
            binding.tvPriorityNumber.text = priorityNumber.toString()
            binding.root.setOnClickListener {
                lastPosition = selectedPosition
                selectedPosition = adapterPosition
                onItemClick(priorityNumber)
                notifyItemChanged(lastPosition)
                notifyItemChanged(selectedPosition)
            }
            if (selectedPosition == adapterPosition) {
                binding.root.backgroundTintList = ColorStateList.valueOf(itemView.resources.getColor(R.color.secondary_color))
            } else {
                binding.root.backgroundTintList = ColorStateList.valueOf(itemView.resources.getColor(R.color.grey_navigation))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PriorityAdapterViewHolder {
        binding = ItemPriorityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PriorityAdapterViewHolder(binding)
    }

    override fun getItemCount() = k

    override fun onBindViewHolder(holder: PriorityAdapterViewHolder, position: Int) {
        holder.bind(generatePriorityOrder(k)[position], onItemClick)
    }

    private fun generatePriorityOrder(k: Int) = (1..k).toList()

}