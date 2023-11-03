package com.kunal.uptodo.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kunal.uptodo.constants.PageName
import com.kunal.uptodo.databinding.FragmentIndexBinding

class IndexFragment : BaseFragment() {

    private lateinit var binding: FragmentIndexBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIndexBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = binding.run {
        //todo recyler view for items later
    }


    override fun getPageName() = PageName.IndexFragment

    companion object {
        @JvmStatic
        fun newInstance() = IndexFragment()
        const val INDEX = "Index"
    }
}