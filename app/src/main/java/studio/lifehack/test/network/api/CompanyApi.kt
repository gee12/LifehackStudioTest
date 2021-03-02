package studio.lifehack.test.network.api

import retrofit2.Response
import studio.lifehack.test.network.Result
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import studio.lifehack.test.model.Company

/**
 * Используется для отправки запросов на сервер с использованием OAuth токена.
 */
interface CompanyApi {

    @GET("test_task/test.php")
    suspend fun loadCompanies() : Result<List<Company>>

    @GET("test_task/test.php")
    suspend fun loadCompany(
        @Query("id") id : Int
    ) : Result<List<Company>>
}