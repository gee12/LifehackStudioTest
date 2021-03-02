package studio.lifehack.test.network

import com.google.gson.Gson
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
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

            // попытка поддержки на API 19
//            val spec = listOf(ConnectionSpec.Builder(ConnectionSpec.COMPATIBLE_TLS)
//                .tlsVersions(TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0)
//                .cipherSuites(
//                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
//                    CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256,
//                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA,
//                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA
//                )
//                .build())
//            val spec = listOf(ConnectionSpec.CLEARTEXT,
//                ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
//                    .allEnabledTlsVersions()
//                    .allEnabledCipherSuites()
//                    .build())

            return OkHttpClient().newBuilder()
//                .connectionSpecs(spec)
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
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
