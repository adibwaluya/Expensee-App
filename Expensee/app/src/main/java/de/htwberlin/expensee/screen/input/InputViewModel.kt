package de.htwberlin.expensee.screen.input


import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class InputViewModel() : ViewModel() {

    private var TAG = "InputViewModel"
    private val budgetCollectionRef = Firebase.firestore
        .collection("sampleData")

    fun vmSaveInput(input: Input) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val localTime = System.currentTimeMillis()
            budgetCollectionRef.document(localTime.toString()).set(input).await()
        }
        catch (e: Exception) {
            Log.d(TAG, e.toString())
        }
    }

}
