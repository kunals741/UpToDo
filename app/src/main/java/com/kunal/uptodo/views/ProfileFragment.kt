package com.kunal.uptodo.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kunal.uptodo.constants.PageName
import com.kunal.uptodo.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = binding.run{
        //todo later
    }

    override fun getPageName() = PageName.ProfileFragment

    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()
        const val PROFILE = "Profile"
    }
}