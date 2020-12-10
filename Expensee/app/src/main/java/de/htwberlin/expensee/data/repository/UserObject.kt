package de.htwberlin.expensee.data.repository

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.Exclude
import java.io.Serializable

class UserObject : LiveData<FirebaseUser?>() {

    // Added 10.12.2020
    private val firebaseAuth = FirebaseAuth.getInstance()

    private val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
        value = firebaseAuth.currentUser
    }

    override fun onActive() {
        firebaseAuth.addAuthStateListener(authStateListener)
    }

    override fun onInactive() {
        firebaseAuth.removeAuthStateListener { authStateListener }
    }

    /*
    var userName: String? = null
    var userEmail: String? = null
    var userPassword: String? = null

    @Exclude
    var isAuthenticated = false
    @Exclude
    var isNew = false
    @Exclude
    var isCreated = false

     */
}