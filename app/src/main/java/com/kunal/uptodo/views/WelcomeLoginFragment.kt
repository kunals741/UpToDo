package com.kunal.uptodo.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kunal.uptodo.databinding.FragmentWelcomeLoginBinding

class WelcomeLoginFragment : BaseFragment() {
    private lateinit var binding: FragmentWelcomeLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() = binding.run {
        ivBack.setOnClickListener {
            activityHandleBack()
        }
        btnLogin.setOnClickListener {
            //todo
        }
        btnRegister.setOnClickListener {
            //todo
        }
    }

    companion object {
        @JvmStatic
        fun getInstance() = WelcomeLoginFragment()
    }


}