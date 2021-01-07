package de.htwberlin.expensee.screen.mainpage

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import de.htwberlin.expensee.databinding.FragmentMainPageBinding
import de.htwberlin.expensee.screen.input.Input
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.StringBuilder

class MainPageViewModel : ViewModel() {

    // Updated on 06.01.2021
    private val budgetCollectionRef = Firebase.firestore
            .collection("sampleData")

    val sb = StringBuilder()

    // Test for db
    private var _mDocRef : DocumentReference = FirebaseFirestore.getInstance()
        .document("sampleData/inputs")
    val mDocRef
        get() = _mDocRef

    lateinit var data : Map<String, Any>

    // Updated on 06.01.2021
    // TODO: Fix this

    fun vmRetrieveInput() = CoroutineScope(Dispatchers.IO).launch {
        val querySnapshot = budgetCollectionRef.get().await()

        //val sb = StringBuilder()
        for (document in querySnapshot.documents) {

            val income = document.toObject<Input>()
            sb.append("$income\n")
        }
    }

    // Fetch function
    fun fetchInput() {
        _mDocRef.get().addOnSuccessListener { DocumentSnapshot ->
            var dataFromDb = DocumentSnapshot.getData()
            if (dataFromDb.isNullOrEmpty())
                data = dataFromDb as Map<String, Any>
            else data = dataFromDb as Map<String, Any>
        }
    }

}