package studio.lifehack.test.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import studio.lifehack.test.model.Company
import studio.lifehack.test.network.Result
import studio.lifehack.test.repo.RemoteRepository
import studio.lifehack.test.ui.BaseViewModel
import studio.lifehack.test.utils.ErrorType
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    val repository: RemoteRepository
) : BaseViewModel() {

    private val _mutableCompany = MutableLiveData<Company>()
    val company: LiveData<Company> get() = _mutableCompany

    fun loadCompany(id: Int) {
        val isLoadingNow = (loadingState.value ?: false)
        if (!isLoadingNow) {
            viewModelScope.launch {
                _mutableLoadingState.value = true

                val result = repository.loadCompany(id)
                when (result) {
                    is Result.Success -> {
                        // на всякий случай проверяем есть ли элемент в списке
                        result.data?.let {
                            if (it.isNotEmpty()) {
                                _mutableCompany.value = it.first()
                            } else {
                                _mutableError.value = ErrorType.EmptyList
                            }
                        }
                    }
                    is Result.Failure -> {
                        _mutableError.value = ErrorType.FailureResponse
                    }
                    else -> {
                        _mutableError.value = ErrorType.NetworkError
                    }
                }

                _mutableLoadingState.value = false
            }
        }
    }

}