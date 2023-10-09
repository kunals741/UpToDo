package com.kunal.uptodo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kunal.uptodo.databinding.ItemOnboardingBinding
import com.kunal.uptodo.models.OnboardingItem

class OnboardingViewPagerAdapter(private val onboardingItemList: List<OnboardingItem>) : RecyclerView.Adapter<OnboardingViewPagerAdapter.OnboardingViewPagerViewHolder>() {

    override fun getItemCount() = onboardingItemList.size
    private lateinit var binding: ItemOnboardingBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OnboardingViewPagerViewHolder {
        binding = ItemOnboardingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OnboardingViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnboardingViewPagerViewHolder, position: Int) {
        holder.bind(onboardingItemList[position])
    }

    class OnboardingViewPagerViewHolder(private val binding: ItemOnboardingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(onboardingItemList: OnboardingItem) = binding.run {
            ivMain.setImageResource( onboardingItemList.image)
            tvTitle.text = onboardingItemList.title
            tvSubTitle.text = onboardingItemList.subTitle
        }
    }
}