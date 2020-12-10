package de.htwberlin.expensee.data.repository

import android.app.Application
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.google.android.play.core.tasks.OnCompleteListener
import com.google.android.play.core.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

// TODO: Reevaluate constructor of the class
class AppRepository(
    

    application: Application,
    userMutableLiveData: MutableLiveData<FirebaseUser>,
    firebaseAuth: FirebaseAuth) {

    /*

    private val application = application
    private val userMutableLiveData = MutableLiveData<FirebaseUser>()

    // Used for Register and Sign in
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val user: UserObject = UserObject()




    // TODO: Delete soon if this is unnecessary to begin with
    fun checkIfUserIsAuthenticatedInFireBase() : MutableLiveData<UserObject> {
        val isUserAuthenticate = MutableLiveData<UserObject>()
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser == null) {
            user.isAuthenticated = false
            isUserAuthenticate.setValue(user)
        } else {
            user.userEmail = firebaseUser.email
            user.isAuthenticated = true
            isUserAuthenticate.setValue(user)
        }
        return isUserAuthenticate
    }

    fun register(email: String, password: String) {
        this.firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Registration OK
                    userMutableLiveData.postValue(firebaseAuth.currentUser)
                } else {
                    // Giving the Meldung
                    Toast.makeText(application, "Registration Failed: " + task.exception!!.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    /*
    Option 2
    // TODO: Why addOnCompleteListener here doesn't work!??
    @RequiresApi(Build.VERSION_CODES.P)
    fun register(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(application.mainExecutor, OnCompleteListener<AuthResult> {
                fun onComplete(task: Task<AuthResult>?) {
                    if (task!!.isSuccessful) {
                        userMutableLiveData.postValue(firebaseAuth.currentUser)
                    } else {
                        Toast.makeText(application, "Registration Failed: " + task.exception.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })
    }

     */

    val getUserMutableLiveData = userMutableLiveData

     */

}