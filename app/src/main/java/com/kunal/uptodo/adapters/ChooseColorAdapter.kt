package com.kunal.uptodo.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kunal.uptodo.databinding.ItemColorBinding

class ChooseColorAdapter(private val colorList: List<String>) : RecyclerView.Adapter<ChooseColorAdapter.ChooseColorAdapterViewHolder>() {

    private lateinit var binding: ItemColorBinding

    inner class ChooseColorAdapterViewHolder(binding: ItemColorBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(color: String) = binding.run {
            root.backgroundTintList = ColorStateList.valueOf(Color.parseColor(color))
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChooseColorAdapterViewHolder {
        binding = ItemColorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChooseColorAdapterViewHolder(binding)
    }

    override fun getItemCount() = colorList.size

    override fun onBindViewHolder(holder: ChooseColorAdapterViewHolder, position: Int) {
        holder.bind(colorList[position])
    }

}