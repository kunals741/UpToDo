package com.kunal.uptodo.adapters

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kunal.uptodo.databinding.ItemCategoryBinding
import com.kunal.uptodo.models.CategoryListModel

class CategoryAdapter(
    private val categoryList: List<CategoryListModel>,
    private val onItemClick: ((String) -> Unit)?
) : RecyclerView.Adapter<CategoryAdapter.CategoryAdapterViewHolder>() {

    private lateinit var binding: ItemCategoryBinding

    inner class CategoryAdapterViewHolder(binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(categoryItem: CategoryListModel, onItemClick: ((String) -> Unit)?) = binding.run {
            ivCategory.setBackgroundDrawable(itemView.resources.getDrawable(categoryItem.imageDrawable))
            tvCategory.text = categoryItem.categoryName
            root.setOnClickListener {
                onItemClick?.invoke(categoryItem.categoryName)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapterViewHolder {
        binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryAdapterViewHolder(binding)
    }

    override fun getItemCount() = categoryList.size

    override fun onBindViewHolder(holder: CategoryAdapterViewHolder, position: Int) {
        holder.bind(categoryList[position], onItemClick)
    }
}

class GridSpacingItemDecoration(
    private val horizontalSpacing: Int,
    private val verticalSpacing: Int,
    private val spanCount: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        outRect.left = column * horizontalSpacing / spanCount
        outRect.right = horizontalSpacing - (column + 1) * horizontalSpacing / spanCount

        if (position < spanCount) {
            outRect.top = verticalSpacing
        }
        outRect.bottom = verticalSpacing
    }
}