package de.htwberlin.expensee.screen.registration

import android.app.Application
import androidx.lifecycle.ViewModel
import de.htwberlin.expensee.data.repository.AppRepository

class UserRegistrationViewModel() : ViewModel() {

    private lateinit var appRepository: AppRepository
    private var userMutableLiveData = appRepository.getUserMutableLiveData

    fun register(email: String, password: String) {
        appRepository.register(email, password)
    }
    var getUserMutableLiveData = userMutableLiveData
}