package de.htwberlin.expensee.screen.input

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import de.htwberlin.expensee.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class InputViewModel : ViewModel() {

    // Updated on 04.01.2021
    var money: Double = 0.0
    var desc: String = ""
    @RequiresApi(Build.VERSION_CODES.O)
    val localDateTime = LocalDateTime.now()
    private val budgetCollectionRef = Firebase.firestore
        .document("sampleData/" + localDateTime)

    // Updated on 04.01.2021
    fun vmSaveInput(input: Input) = CoroutineScope(Dispatchers.IO).launch {
        // Wrap the data uploading process around try and catch block
        try {
            budgetCollectionRef.set(input).await()
            withContext(Dispatchers.Main) {
                //Toast.makeText(this@InputViewModel, "Successfully saved data", Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                //Toast.makeText(this@InputViewModel, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    // TODO: Buat apa ya???
    private var _inputValue = MutableLiveData<Double>(300.0)
    val inputValue: MutableLiveData<Double>
        get() = _inputValue

    fun setValue() {
        Log.d("InputPage", "Button Clicked!")
        _inputValue = MutableLiveData(350.0)
    }
}
