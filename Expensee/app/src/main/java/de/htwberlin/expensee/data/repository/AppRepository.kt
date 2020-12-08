package de.htwberlin.expensee.data.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.android.play.core.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AppRepository(application: Application) {

    private val app = application
    private var userMutableLiveData = MutableLiveData<FirebaseUser>()
    private lateinit var firebaseAuth: FirebaseAuth                     // Used for Register and Sign in

    public fun register(email: String, password: String) {
        this.firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Registration OK
                    userMutableLiveData.postValue(firebaseAuth.currentUser)
                } else {
                    // Giving the Meldung
                    Toast.makeText(app, "Registration Failed: " + task.exception!!.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    var getUserMutableLiveData = userMutableLiveData

}