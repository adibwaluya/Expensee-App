package de.htwberlin.expensee.screen.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import de.htwberlin.expensee.data.repository.UserObject

class UserLoginViewModel() : ViewModel() {

    // Added 10.12.2020
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

    /*
    private lateinit var appRepository: AppRepository
    private var userMutableLiveData = appRepository.getUserMutableLiveData

    fun register(email: String, password: String) {
        appRepository.register(email, password)
    }

    var getUserMutableLiveData = userMutableLiveData


     */
}