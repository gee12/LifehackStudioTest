package studio.lifehack.test.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Suppress("unused")
@Module
interface AppModule {

    @Binds
    fun bindContext(application: Application): Context
}