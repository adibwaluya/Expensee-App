package de.htwberlin.expensee.screen.input

import android.app.Application
import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import de.htwberlin.expensee.MainActivity
import de.htwberlin.expensee.databinding.FragmentInputBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.StringBuilder
import java.time.LocalDateTime

class InputViewModel() : ViewModel() {

    // Updated on 04.01.2021
    var money: Double = 0.0
    var desc: String = ""
    @RequiresApi(Build.VERSION_CODES.O)
    val localDateTime = LocalDateTime.now()

    // Updated 06.01.2021,
    // TODO: Ask mike, last written -> firestore.document(??)
    // TODO: LocalDateTime to be added (if necessary)
    private val budgetCollectionRef = Firebase.firestore
        .collection("sampleData")

    // Updated on 04.01.2021
    fun vmSaveInput(input: Input) = CoroutineScope(Dispatchers.IO).launch {
        // Wrap the data uploading process around try and catch block
        try {
            // Write 'set' for document, 'add' for collection
            // TODO: Use MutableLiveData for Toast!
            budgetCollectionRef.add(input).await()
            withContext(Dispatchers.Main) {
                //Toast.makeText(this@InputViewModel, "Successfully saved data", Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                //Toast.makeText(this@InputViewModel, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    // TODO: Buat apa ya??? -> Platzhalter aja sihh. Kosongin aja kah?
    private var _inputValue = MutableLiveData<Double>(300.0)
    val inputValue: MutableLiveData<Double>
        get() = _inputValue

    fun setValue() {
        Log.d("InputPage", "Button Clicked!")
        _inputValue = MutableLiveData(350.0)
    }
}
