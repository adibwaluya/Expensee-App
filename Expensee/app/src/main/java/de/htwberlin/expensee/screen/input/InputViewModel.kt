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

    private val budgetCollectionRef = Firebase.firestore
        .collection("sampleData")

    fun vmSaveInput(input: Input) = CoroutineScope(Dispatchers.IO).launch {
        // Wrap the data uploading process around try and catch block

        val localTime = System.currentTimeMillis()
        budgetCollectionRef.document(localTime.toString()).set(input).await()

    }

}
