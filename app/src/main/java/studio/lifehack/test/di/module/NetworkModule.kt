package studio.lifehack.test.di.module

import android.content.Context
import coil.ImageLoader
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import studio.lifehack.test.BuildConfig
import studio.lifehack.test.network.ServiceGenerator
import studio.lifehack.test.network.TestAuthenticator
import studio.lifehack.test.network.TestInterceptor
import studio.lifehack.test.network.api.CompanyApi
import javax.inject.Singleton

@Suppress("unused")
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideGson() = GsonBuilder()
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideAuthHttpClient(
        authInterceptor: TestInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
        tokenAuthenticator: TestAuthenticator
    ): OkHttpClient {
        val interceptors = arrayOf(authInterceptor, loggingInterceptor)
        return ServiceGenerator.createHttpClient(tokenAuthenticator, interceptors)
    }

    @Singleton
    @Provides
    fun provideAuthApi(
        gson: Gson,
        httpClient: OkHttpClient
    ): CompanyApi {
        return ServiceGenerator.generate(BuildConfig.BASE_URL, CompanyApi::class.java, gson, httpClient)
    }

    @Singleton
    @Provides
    fun buildImageLoader(
        context: Context,
        httpClient: OkHttpClient
    ): ImageLoader {
        return ImageLoader.Builder(context)
            .okHttpClient(httpClient)
            .build()
    }
}