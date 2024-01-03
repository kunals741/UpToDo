package com.kunal.uptodo.views

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kunal.uptodo.R
import com.kunal.uptodo.constants.AppConstants.GOOGLE_SIGN_IN_REQ_CODE
import com.kunal.uptodo.constants.IntentKeyConstants
import com.kunal.uptodo.constants.PageName
import com.kunal.uptodo.databinding.ActivityLoginRegisterBinding
import com.kunal.uptodo.shared_pref.UserSession
import com.kunal.uptodo.utils.hideKeyboard
import com.kunal.uptodo.utils.showToast
import com.kunal.uptodo.utils.validateEmail

class LoginRegisterActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginRegisterBinding
    private var isNewUser = false
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private val userSession by lazy { UserSession(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        auth = Firebase.auth
        isNewUser = intent.getBooleanExtra(IntentKeyConstants.IS_NEW_USER, false)
        initView()
        initListeners()
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
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
            btnLogin.text = getString(R.string.login)
        } else {
            tvTitle.text = getString(R.string.register)
            tvLabelConfirmPassword.isVisible = true
            etConfirmPassword.isVisible = true
            tvAlreadyHaveAccount.text = getString(R.string.already_have_an_account)
            tvLogin.text = getString(R.string.login)
            btnLoginWithGoogle.text = getString(R.string.register_with_google)
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
        //todo clean this code later
        btnLogin.setOnClickListener {
            this@LoginRegisterActivity.hideKeyboard()
            //todo add progess dialog
            var validEmail: String? = null
            var validPassword: String? = null
            if (getEmailInput().isNotEmpty() && validateEmail(getEmailInput())) {
                validEmail = getEmailInput()
            } else {
                showToast(this@LoginRegisterActivity, "Enter valid Email address!")
                return@setOnClickListener
            }

            if (getPasswordInput().length < 6) {
                showToast(this@LoginRegisterActivity, "Password should be at least 6 characters")
                return@setOnClickListener
            }

            if ((isNewUser && getConfirmPassword() == getPasswordInput())) {
                validPassword = getPasswordInput()
            } else if (isNewUser) {
                showToast(this@LoginRegisterActivity, "Please make sure, your password match")
                return@setOnClickListener
            }

            if (isNewUser && validPassword != null) {
                registerUser(validEmail, validPassword)
            }

            if (!isNewUser && getPasswordInput().isNotEmpty()) {
                signInUser(validEmail, getPasswordInput())
            }
        }

        btnLoginWithGoogle.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, GOOGLE_SIGN_IN_REQ_CODE)
        }
    }

    private fun registerUser(validEmail: String, validPassword: String) {
        auth.createUserWithEmailAndPassword(validEmail, validPassword)
            .addOnCompleteListener(this@LoginRegisterActivity) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    //todo use this user to fetch data:
                    startHomeActivity()
                } else {
                    Log.d(pageType(), task.exception.toString())
                    showToast(this@LoginRegisterActivity, "Authentication failed.")
                }
            }
    }

    private fun signInUser(validEmail: String, password: String) {
        auth.signInWithEmailAndPassword(validEmail, getPasswordInput())
            .addOnCompleteListener(this@LoginRegisterActivity) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    //todo use user
                    startHomeActivity()
                } else {
                    showToast(this@LoginRegisterActivity, "Authentication failed.")
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == GOOGLE_SIGN_IN_REQ_CODE) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                showToast(this, "Authentication failed.")
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    startHomeActivity()
                } else {
                    showToast(this, "Authentication failed.")
                }
            }
    }

    private fun startHomeActivity() {
        userSession.setLoggedIn(true)
        HomeActivity.startHomeActivity(
            pageType(),
            this@LoginRegisterActivity
        )
    }

    override fun handleBack() {
        onBackPressed()
    }

    private fun getEmailInput() = binding.etEmailId.text.toString()
    private fun getPasswordInput() = binding.etPassword.text.toString()
    private fun getConfirmPassword() = binding.etConfirmPassword.text.toString()

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
            }
            activity.startActivity(intent)
        }
    }
}