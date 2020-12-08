package de.htwberlin.expensee.screen.input

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InputViewModel : ViewModel() {
    private var _inputValue = MutableLiveData<Float>(300.01f)
    val inputValue: MutableLiveData<Float>
        get() = _inputValue

    fun setValue() {
        _inputValue = MutableLiveData(350f)
    }
}