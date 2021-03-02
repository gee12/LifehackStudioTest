package studio.lifehack.test.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import studio.lifehack.test.network.api.CompanyApi
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val companyApi: CompanyApi
) {

    suspend fun loadCompanies() = withContext(Dispatchers.IO) {
        companyApi.loadCompanies()
    }

    suspend fun loadCompany(id: Int) = withContext(Dispatchers.IO) {
        companyApi.loadCompany(id)
    }
}