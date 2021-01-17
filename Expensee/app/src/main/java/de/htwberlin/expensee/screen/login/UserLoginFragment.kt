package de.htwberlin.expensee.screen.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import de.htwberlin.expensee.R
import de.htwberlin.expensee.databinding.FragmentLoginRegisterBinding
import kotlin.math.log

class UserLoginFragment : Fragment() {

    companion object {
        const val TAG = "LoginFragment"
        const val SIGN_IN_RESULT_CODE = 1001
    }
    private lateinit var binding: FragmentLoginRegisterBinding
    private val viewModel by viewModels<UserLoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = DataBindingUtil.inflate(
           inflater,
           R.layout.fragment_login_register,
           container,
           false
       )

        binding.authButton.text = getString(R.string.log_in_button)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeAuthenticationState()

        binding.authButton.setOnClickListener { launchSignInFlow() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SIGN_IN_RESULT_CODE) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                // User successfully signed in
                Log.i(
                    TAG,
                    "Succesfully signed in user ${FirebaseAuth.getInstance().currentUser?.displayName}!"
                )
            } else {
                // Sign in failed. If response is null the user cancelled the sign-in flow
                Log.i(TAG, "Sign in unsuccessful ${response?.error?.errorCode}")
            }
        }
    }

    private fun observeAuthenticationState() {

        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            when(authenticationState) {
                UserLoginViewModel.AuthenticationState.AUTHENTICATED -> {
                    // User Authenticated and Logged in
                    binding.authButton.text = getString(R.string.Log_out)
                    binding.authButton.setOnClickListener {
                        AuthUI.getInstance().signOut(requireContext())
                    }
                    binding.expenseeLogo.isVisible = false
                    binding.expenseePhrase.isVisible = false
                    binding.buffetPhrase.isVisible = true
                    binding.openDataButton.isVisible = true
                    binding.openDataButton.setOnClickListener { view: View ->
                        view.findNavController().navigate(R.id.action_userLoginFragment_to_mainPageFragment)

                    }

                    Toast.makeText(activity, "No Internet Connection!", Toast.LENGTH_LONG).show()
                }

                else -> {
                    // Launch SignInFlow()
                    binding.openDataButton.isVisible = false
                    binding.buffetPhrase.isInvisible = true
                    binding.expenseeLogo.isVisible = true
                    binding.expenseePhrase.isInvisible = false
                    binding.authButton.text = getString(R.string.proceed_button)
                    binding.authButton.setOnClickListener { launchSignInFlow() }
                }

            }
        })
    }

    private fun launchSignInFlow() {

        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )

        try {
            // Create and launch sign-in intent
            startActivityForResult(
                    AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(
                            providers
                    ).build(), SIGN_IN_RESULT_CODE
            )

        } catch (e: Exception) {
            Log.e(TAG, e.toString())
            Toast.makeText(activity, "No Internet Connection!", Toast.LENGTH_LONG).show()
        }

    }

}