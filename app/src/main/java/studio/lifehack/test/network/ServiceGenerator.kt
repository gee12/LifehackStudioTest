package studio.lifehack.test.network

import com.google.gson.Gson
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import studio.lifehack.test.network.Tls12SocketFactory.Companion.enableTls12
import java.util.concurrent.TimeUnit


class ServiceGenerator {

    companion object {

        private const val CONNECTION_TIMEOUT = 15L

        fun <T> generate(
            baseUrl: String,
            serviceClass: Class<T>,
            gson: Gson,
            okHttpClient: OkHttpClient
        ): T {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(MyCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(serviceClass)
        }

        fun createHttpClient(
            authenticator: Authenticator?,
            interceptors: Array<Interceptor>
        ): OkHttpClient {

            return OkHttpClient().newBuilder()
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .enableTls12() // включение TLS 1.2 на устройствах с API < 22
                .apply {
                    for (interceptor in interceptors) {
                        addInterceptor(interceptor)
                    }
                    authenticator?.let {
                        authenticator(it)
                    }
                }
                .build()
        }
    }
}
