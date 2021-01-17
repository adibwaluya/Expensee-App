package de.htwberlin.expensee.screen.login

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import androidx.core.app.ActivityCompat.recreate
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import de.htwberlin.expensee.MainActivity
import de.htwberlin.expensee.data.repository.UserObject


class UserLoginViewModel() : ViewModel() {

    enum class AuthenticationState {
        AUTHENTICATED, UNAUTHENTICATED, INVALID_AUTHENTICATED
    }

    val authenticationState: LiveData<AuthenticationState> = UserObject().map{ user ->
        if (user != null) {
            AuthenticationState.AUTHENTICATED
        } else {
            AuthenticationState.UNAUTHENTICATED
        }
    }

}