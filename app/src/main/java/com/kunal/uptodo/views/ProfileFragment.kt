package com.kunal.uptodo.views

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.kunal.uptodo.R
import com.kunal.uptodo.constants.PageName
import com.kunal.uptodo.databinding.FragmentProfileBinding
import com.kunal.uptodo.shared_pref.UserSession

class ProfileFragment : BaseFragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private val auth by lazy {
        FirebaseAuth.getInstance()
    }

    private val userSession by lazy { UserSession(requireContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
    }

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

    private fun initView() = binding.run {
        //todo later
        btnLogout.setOnClickListener {
            googleSignInClient.signOut().addOnCompleteListener { task ->
                // Check condition
                if (task.isSuccessful) {
                    auth.signOut()
                    userSession.setLoggedIn(false)
                    userSession.clearPref()
                    Toast.makeText(requireContext(), "Logout successful", Toast.LENGTH_SHORT).show()
                    MainActivity.startMainActivity(getPageName(), requireContext())
                    requireActivity().finish()
                }
            }
        }
    }

    override fun getPageName() = PageName.ProfileFragment

    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()
        const val PROFILE = "Profile"
    }
}