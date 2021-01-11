package de.htwberlin.expensee.screen.mainpage

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
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

class MainPageViewModel : ViewModel() {

    // TODO: Clean up and double check Zugriffsrechte (private or public etc)
    private val budgetCollectionRef = Firebase.firestore
            .collection("sampleData")

    private var _mDocRef : DocumentReference = FirebaseFirestore.getInstance()
        .document("saldo/current")

    private val sb = StringBuilder()
    private var cs = .0
    private val _inputData = MutableLiveData<String>()
    val inputData: LiveData<String>
        get() = _inputData
    private val _saldo = MutableLiveData<Double>()
    val saldo: LiveData<Double>
        get() = _saldo

    lateinit var data : Map<String, Any>

    // TODO: Clean up
    fun vmRetrieveInput() = CoroutineScope(Dispatchers.IO).launch {
        val querySnapshot = budgetCollectionRef.get().await()

        //val sb = StringBuilder()
        for (document in querySnapshot.documents) {

            val income = document.toObject<Input>()
            if (income != null) {
                sb.append("${income.description} : ${income.amountMoney} € \n")
                cs += income.amountMoney
                // _saldo.value = _saldo.value?.plus(income.amountMoney)
            }
        }
        var currentSaldo = mutableMapOf<String, Double>()
        currentSaldo["input"] = cs  //_saldo.toString().toDouble()
        withContext(Dispatchers.Main){
            _inputData.value = sb.toString()
            _saldo.value = cs
            _mDocRef.set(currentSaldo).addOnSuccessListener {
                Log.d("Main Page", "current saldo updated!")
            }
            Log.d("CURRENT SALDO: ", _mDocRef.get().toString())
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