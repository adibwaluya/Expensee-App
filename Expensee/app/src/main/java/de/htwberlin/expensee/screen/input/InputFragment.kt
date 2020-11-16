package de.htwberlin.expensee.screen.input

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import de.htwberlin.expensee.R
import de.htwberlin.expensee.databinding.FragmentInputBinding

class InputFragment : Fragment() {

    private lateinit var binding: FragmentInputBinding
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

        binding.vorzeichenButton.setOnClickListener {  // Change the color of input whether it is positive or negative
            var input = binding.transactionInput
            if (vorzeichenClick % 2 == 0) {
                input.setTextColor(Color.GREEN)
            }
            else {
                input.setTextColor(Color.RED)
            }
        }

        return binding.root
    }
}