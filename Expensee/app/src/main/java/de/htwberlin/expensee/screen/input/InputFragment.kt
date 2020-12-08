package de.htwberlin.expensee.screen.input

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import de.htwberlin.expensee.R
import de.htwberlin.expensee.databinding.FragmentInputBinding

class InputFragment : Fragment() {

    private lateinit var binding: FragmentInputBinding
    private lateinit var viewModel: InputViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var vorzeichenClick = 0

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_input,
            container,
            false
        )

        // ViewModel
        viewModel = ViewModelProvider(this).get(InputViewModel::class.java)
        binding.inputViewModel = viewModel
        binding.lifecycleOwner = this

        binding.vorzeichenButton.setOnClickListener {  // Change the color of input whether it is positive or negative
            var input = binding.transactionInput
            if (vorzeichenClick % 2 == 0) {
                input.setTextColor(Color.GREEN)
            }
            else {
                input.setTextColor(Color.RED)
            }
        }

        // TODO: Add observer for finish event?

        return binding.root
    }
}