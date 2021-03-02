package studio.lifehack.test.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Interceptor, используемый для вставки в заголовок запроса данных для авторизации.
 *
 * (в данный момент просто загрушка)
 */
class TestInterceptor @Inject constructor(): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
}