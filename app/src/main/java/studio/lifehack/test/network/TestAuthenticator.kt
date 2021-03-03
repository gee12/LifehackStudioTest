package studio.lifehack.test.network

import okhttp3.*
import javax.inject.Inject

/**
 * Класс для отправки запроса на авторизацию.
 *
 * (в данный момент просто загрушка)
 */
class TestAuthenticator @Inject constructor() : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request {
        return response.request() // для старой версии okhttp3
//        return response.request
    }
}