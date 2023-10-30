package com.kunal.uptodo.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.kunal.uptodo.R
import com.kunal.uptodo.adapters.OnboardingViewPagerAdapter
import com.kunal.uptodo.constants.PageName
import com.kunal.uptodo.databinding.FragmentOnboardingBinding
import com.kunal.uptodo.models.OnboardingItem

class OnboardingFragment : BaseFragment() {

    private lateinit var binding: FragmentOnboardingBinding
    override fun getPageName() = PageName.OnboardingFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = binding.run {
        radioGroup.check(radioGroup.getChildAt(0).id)
        vpBenefits.adapter = OnboardingViewPagerAdapter(getBenefitList())
        vpBenefits.registerOnPageChangeCallback(pageChangeCallBack)
        tvBack.setOnClickListener {
            val currentPosition = vpBenefits.currentItem
            //currently hardcoding the list size:
            if (currentPosition > 0) {
                tvBack.isVisible = true
                vpBenefits.currentItem = currentPosition - 1
            }
        }
        mainActionButton.setOnClickListener {
            val currentPosition = vpBenefits.currentItem
            //currently hardcoding the list size:
            if (currentPosition < 2) {
                vpBenefits.currentItem = currentPosition + 1
            } else {
                showWelcomeLoginFragment()
            }
        }
        tvSkip.setOnClickListener {
            showWelcomeLoginFragment()
        }
    }

    private fun getBenefitList(): List<OnboardingItem> {
        val benefit1 = OnboardingItem(
            R.drawable.ic_onboarding_first,
            getString(R.string.manage_your_tasks),
            getString(R.string.you_can_easily_manage_all_of_your_daily_tasks_in_dome_for_free)
        )
        val benefit2 = OnboardingItem(
            R.drawable.ic_benefit2,
            getString(R.string.create_daily_routine),
            getString(R.string.in_uptodo_you_can_create_your_personalized_routine_to_stay_productive)
        )
        val benefit3 = OnboardingItem(
            R.drawable.ic_benefit3,
            getString(R.string.organize_your_tasks),
            getString(R.string.you_can_organize_your_daily_tasks_by_adding_your_tasks_into_separate_categories)
        )
        return listOf(benefit1, benefit2, benefit3)
    }

    private val pageChangeCallBack = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            binding.apply {
                val view = radioGroup.getChildAt(position)
                radioGroup.check(view.id)
                tvBack.isVisible = position != 0
                if (position == 2) {
                    mainActionButton.text = getString(R.string.get_started)
                } else {
                    mainActionButton.text = getString(R.string.next)
                }
            }
        }
    }

    private fun showWelcomeLoginFragment() =
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, WelcomeLoginFragment.getInstance())
            .addToBackStack(null)
            .commit()


    companion object {
        @JvmStatic
        fun newInstance() = OnboardingFragment()
    }
}