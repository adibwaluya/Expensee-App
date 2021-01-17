package de.htwberlin.expensee.screen.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import de.htwberlin.expensee.data.repository.UserObject
import java.io.IOException


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