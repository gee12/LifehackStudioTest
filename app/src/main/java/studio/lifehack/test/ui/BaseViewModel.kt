package studio.lifehack.test.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import studio.lifehack.test.utils.ErrorType

open class BaseViewModel : ViewModel() {
    protected val _mutableError = MutableLiveData<ErrorType>()
    protected val _mutableLoadingState = MutableLiveData(false)

    val error: LiveData<ErrorType> get() = _mutableError
    val loadingState: LiveData<Boolean> get() = _mutableLoadingState
}