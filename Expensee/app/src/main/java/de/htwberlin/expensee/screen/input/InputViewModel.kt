package de.htwberlin.expensee.screen.input

import android.app.Application
import android.content.Context
import android.os.Build
import android.os.SystemClock
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
import java.math.BigDecimal
import java.time.LocalDateTime
import kotlin.math.round

class InputViewModel() : ViewModel() {

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
            val localTime = System.currentTimeMillis()
            budgetCollectionRef.document(localTime.toString()).set(input).await()

            // TODO: Use MutableLiveData for Toast! -> Still needed?
            withContext(Dispatchers.Main) {
                //Toast.makeText(this@InputViewModel, "Successfully saved data", Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                //Toast.makeText(this@InputViewModel, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    /*
    fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return round(this * multiplier) / multiplier
    }

     */
}
