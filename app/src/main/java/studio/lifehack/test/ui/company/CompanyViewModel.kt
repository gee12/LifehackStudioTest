package studio.lifehack.test.ui.company

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import studio.lifehack.test.model.Company
import studio.lifehack.test.repo.RemoteRepository
import studio.lifehack.test.network.Result
import studio.lifehack.test.ui.BaseViewModel
import studio.lifehack.test.utils.ErrorType
import javax.inject.Inject

class CompanyViewModel @Inject constructor(
    val repository: RemoteRepository
) : BaseViewModel() {

    private val _mutableCompanyList = MutableLiveData<List<Company>>(emptyList())
    val companiesList: LiveData<List<Company>> get() = _mutableCompanyList

    fun loadCompanies() {
        val isLoadingNow = (loadingState.value ?: false)
        if (!isLoadingNow) {
            viewModelScope.launch {
                _mutableLoadingState.value = true

                val result = repository.loadCompanies()
                when (result) {
                    is Result.Success -> {
                        _mutableCompanyList.value = result.data!!
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