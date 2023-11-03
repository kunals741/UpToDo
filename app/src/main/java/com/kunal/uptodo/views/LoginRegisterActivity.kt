package com.kunal.uptodo.views

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import com.kunal.uptodo.R
import com.kunal.uptodo.constants.IntentKeyConstants
import com.kunal.uptodo.constants.PageName
import com.kunal.uptodo.databinding.ActivityLoginRegisterBinding

class LoginRegisterActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginRegisterBinding
    private var isNewUser = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        isNewUser = intent.getBooleanExtra(IntentKeyConstants.IS_NEW_USER, false)
        initView()
        initListeners()
    }

    private fun initView() = binding.run {
        ivBack.setOnClickListener {
            handleBack()
        }
        if (!isNewUser) {
            tvTitle.text = getString(R.string.login)
            tvLabelConfirmPassword.isVisible = false
            etConfirmPassword.isVisible = false
            tvAlreadyHaveAccount.text = getString(R.string.don_t_have_an_account)
            tvLogin.text = getString(R.string.register)
            btnLoginWithGoogle.text = getString(R.string.login_with_google)
            btnLoginWithFacebook.text = getString(R.string.login_with_facebook)
            btnLogin.text = getString(R.string.login)
        } else {
            tvTitle.text = getString(R.string.register)
            tvLabelConfirmPassword.isVisible = true
            etConfirmPassword.isVisible = true
            tvAlreadyHaveAccount.text = getString(R.string.already_have_an_account)
            tvLogin.text = getString(R.string.login)
            btnLoginWithGoogle.text = getString(R.string.register_with_google)
            btnLoginWithFacebook.text = getString(R.string.register_with_facebook)
            btnLogin.text = getString(R.string.register)
        }
    }

    private fun initListeners() = binding.run {
        cvFooter.setOnClickListener {
            startActivity(
                pageType(),
                !isNewUser,
                this@LoginRegisterActivity
            )
        }
        btnLogin.setOnClickListener {
            //todo check for condtions
            HomeActivity.startHomeActivity(
                pageType(),
                this@LoginRegisterActivity
            )
        }
    }

    //todo handle empty text case:
    //todo authentication pending from firestor
    //todo pending login with social media account

    override fun handleBack() {
        onBackPressed()
    }

    override fun pageType() = PageName.LoginRegisterActivity

    companion object {
        @JvmStatic
        fun startActivity(
            source: String,
            isNewUser: Boolean,
            activity: Activity
        ) {
            val intent = Intent(activity, LoginRegisterActivity::class.java)
            intent.apply {
                putExtra(IntentKeyConstants.SOURCE, source)
                putExtra(IntentKeyConstants.IS_NEW_USER, isNewUser)
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            activity.startActivity(intent)
        }
    }
}