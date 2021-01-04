package de.htwberlin.expensee.screen.input

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import de.htwberlin.expensee.R
import de.htwberlin.expensee.databinding.FragmentInputBinding
import kotlinx.coroutines.*
import java.lang.Exception

class InputFragment : Fragment() {

    private lateinit var binding: FragmentInputBinding
    private lateinit var viewModel: InputViewModel
    private var TAG = "Input"

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

        binding.vorzeichenButton.setOnClickListener {
            val amountInput = binding.transactionInput.toString().toFloat()
            val descInfo = binding.inputAmount.toString()
            val input = Input(amountInput, descInfo)
            //vmSaveInput(input)
        }
        /* Commented on 04.01.2021
        TODO: Ask Mike if these functions necessary!
        binding.vorzeichenButton.setOnClickListener { view -> // Change the color of input whether it is positive or negative
            Log.d(TAG, "Button Clicked!")
            var input = binding.transactionInput
            if (vorzeichenClick % 2 == 0) {
                input.setTextColor(Color.GREEN)
            }
            else {
                input.setTextColor(Color.RED)
            }
            saveInput(view)
        }


         */
        // TODO: Add observer for finish event?

        return binding.root
    }

    // Updated 04.01.2021
    private fun vmSaveInput() {
        /*
        var moneyInp: Float = viewModel.money
        var descInp: String = viewModel.desc

        viewModel.vmSaveInput(Input)

         */
    }
    // Test for db
    private var mDocRef : DocumentReference = FirebaseFirestore.getInstance().document("sampleData/inputs")

    fun saveInput(view: View) {
        var inputText : EditText = binding.transactionInput
        var inputValue : Float = inputText.toString().toFloat()

        if (inputValue.isNaN()) { return }
        var dataToSave = mutableMapOf<String, Float>()
        dataToSave.put("Income", inputValue)
        mDocRef.set(dataToSave).addOnSuccessListener {
            Log.d(TAG, "Input has been saved!")
        }
    }
}