package de.htwberlin.expensee.screen.mainpage

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.getField

class MainPageViewModel : ViewModel() {

    // Test for db
    private var _mDocRef : DocumentReference = FirebaseFirestore.getInstance().document("sampleData/inputs")
    val mDocRef
        get() = _mDocRef

    lateinit var data : Map<String, Any>

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