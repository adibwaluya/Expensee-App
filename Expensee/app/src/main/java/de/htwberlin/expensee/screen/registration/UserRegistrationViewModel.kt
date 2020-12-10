package de.htwberlin.expensee.screen.registration

import android.app.Application
import androidx.lifecycle.ViewModel
import de.htwberlin.expensee.data.repository.AppRepository

class UserRegistrationViewModel(
    appRepository: AppRepository
) : ViewModel() {

    // TODO: Reevaluate the constructor of the class
    private lateinit var appRepository: AppRepository
    private val userMutableLiveData = appRepository.getUserMutableLiveData

    fun register(email: String, password: String) {
        appRepository.register(email, password)
    }
    val getUserMutableLiveData = userMutableLiveData
}