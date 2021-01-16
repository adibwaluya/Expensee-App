package de.htwberlin.expensee.screen.input


import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class InputViewModel() : ViewModel() {

    private val budgetCollectionRef = Firebase.firestore
        .collection("sampleData")

    fun vmSaveInput(input: Input) = CoroutineScope(Dispatchers.IO).launch {
        // Wrap the data uploading process around try and catch block

        val localTime = System.currentTimeMillis()
        budgetCollectionRef.document(localTime.toString()).set(input).await()

    }

}
