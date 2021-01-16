package de.htwberlin.expensee.screen.input

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import de.htwberlin.expensee.R
import de.htwberlin.expensee.databinding.FragmentInputBinding
import java.lang.Exception

class InputFragment : Fragment() {

    private lateinit var binding: FragmentInputBinding
    private lateinit var viewModel: InputViewModel
    private var TAG = "InputPage"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

        // Logic for save button
        binding.saveButton.setOnClickListener { view: View ->
            val textInput = binding.transactionInput.text.toString()
            val amountInput = textInput.slice(0..(textInput.length - 2)).toDouble()
            val descInfo = binding.transcationInfo.text.toString()
            val dateInput = binding.DateEt.text.toString()
            if (textInput.isEmpty() || amountInput == 0.0 || descInfo.isEmpty() || dateInput.isEmpty()) {
                Toast.makeText(activity, "Please fill up all the information!", Toast.LENGTH_LONG)
                        .show()
            }
            else {
                try {
                    val input = Input(amountInput, descInfo, dateInput)
                    Log.d(TAG, "Data class Input created!")
                    viewModel.vmSaveInput(input)
                    Toast.makeText(activity, "Input saved!", Toast.LENGTH_LONG).show()
                }
                catch (e: Exception) {
                    Toast.makeText(activity, e.message, Toast.LENGTH_LONG).show()
                }

                view.findNavController().navigate(R.id.action_inputFragment_to_mainPageFragment)
            }

        }

        //  Logic for sign button
        binding.vorzeichenButton.setOnClickListener { view ->
            Log.d(TAG, "Vorzeichen!")
            val textInput = binding.transactionInput.text.toString()
            val amountInput = textInput.slice(0..(textInput.length - 2)).toDouble()
            val endInput = amountInput * -1
            val endString = "$endInput €"
            binding.transactionInput.setText(endString)

            if (endInput >= 0) {
                binding.transactionInput.setTextColor(Color.GREEN)
            }
            else {
                binding.transactionInput.setTextColor(Color.RED)
            }
        }

        return binding.root
    }
}