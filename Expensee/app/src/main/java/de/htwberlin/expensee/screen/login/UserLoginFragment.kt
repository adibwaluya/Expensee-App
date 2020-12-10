package de.htwberlin.expensee.screen.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import de.htwberlin.expensee.R
import de.htwberlin.expensee.databinding.FragmentLoginBinding

class UserLoginFragment : Fragment() {

    // Added 10.12.2020
    companion object {
        const val TAG = "LoginFragment"
        const val SIGN_IN_RESULT_CODE = 1001
    }
    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<UserLoginViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        binding.logInButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_userLoginFragment_to_mainPageFragment)
        }
        binding.signUpButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_userLoginFragment_to_userRegistrationFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeAuthenticationState()
    }

    private fun observeAuthenticationState() {
        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            when(authenticationState) {
                UserLoginViewModel.AuthenticationState.AUTHENTICATED -> {
                    // User Authenticated and Logged in
                    binding.logInButton.setOnClickListener { view: View ->
                        view.findNavController().navigate(R.id.action_userLoginFragment_to_mainPageFragment)
                    }
                }

                else -> {
                    // Launch SignInFlow()
                }

            }
        })
    }

}