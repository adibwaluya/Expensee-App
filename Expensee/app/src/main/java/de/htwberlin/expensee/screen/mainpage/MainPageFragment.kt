package de.htwberlin.expensee.screen.mainpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import de.htwberlin.expensee.R
import de.htwberlin.expensee.databinding.FragmentMainPageBinding

class MainPageFragment : Fragment() {

    private lateinit var binding: FragmentMainPageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main_page,
            container,
            false
        )

        binding.inputButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_mainPageFragment_to_inputFragment)
        }

        return binding.root
    }
}