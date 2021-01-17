package de.htwberlin.expensee.screen.mainpage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import de.htwberlin.expensee.screen.input.Input
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.StringBuilder
import java.text.DecimalFormat

class MainPageViewModel : ViewModel() {
    private val budgetCollectionRef = Firebase.firestore
            .collection("sampleData")

    private var _mDocRef : DocumentReference = FirebaseFirestore.getInstance()
        .document("saldo/current")

    private val rootRef = Firebase.firestore
            .collection("id")

    private val sb = StringBuilder()
    private var cs = .00
    private val _inputData = MutableLiveData<String>()

    val inputData: LiveData<String>
        get() = _inputData

    private val _saldo = MutableLiveData<Double>()
    val saldo: LiveData<Double>
        get() = _saldo

    lateinit var data : Map<String, Any>

    fun vmRetrieveInput() = CoroutineScope(Dispatchers.IO).launch {

        val querySnapshot = budgetCollectionRef.get().await()

        // Clear any previous data
        sb.clear()
        cs = .0

        for (document in querySnapshot.documents) {
            val income = document.toObject<Input>()
            val dec = DecimalFormat("#,##0.00")
            if (income != null) {
                sb.insert(0, "[${income.date}] ${income.description} : ${dec.format(income.amountMoney)} € \n")
                cs += income.amountMoney
            }
        }
        var currentSaldo = mutableMapOf<String, Double>()
        currentSaldo["input"] = cs

        withContext(Dispatchers.Main){  // Processes on main thread
            _inputData.value = sb.toString()
            _saldo.value = cs
            _mDocRef.set(currentSaldo).addOnSuccessListener {
                Log.d("Main Page", "current saldo updated!")
            }
            rootRef.orderBy("idNumber", Query.Direction.DESCENDING)
            Log.d("CURRENT SALDO: ", _mDocRef.get().toString())
        }
    }

}