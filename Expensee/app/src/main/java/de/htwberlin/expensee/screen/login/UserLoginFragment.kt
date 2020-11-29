package de.htwberlin.expensee.screen.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import de.htwberlin.expensee.R
import de.htwberlin.expensee.databinding.FragmentLoginBinding

class UserLoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
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

}