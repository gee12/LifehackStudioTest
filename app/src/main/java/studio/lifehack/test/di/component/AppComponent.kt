package studio.lifehack.test.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import studio.lifehack.test.TestApp
import studio.lifehack.test.di.module.AppModule
import studio.lifehack.test.di.module.FragmentBuildersModule
import studio.lifehack.test.di.module.MainActivityModule
import studio.lifehack.test.di.module.NetworkModule
import javax.inject.Singleton

@Suppress("unused")
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    NetworkModule::class,
    MainActivityModule::class,
    FragmentBuildersModule::class
])
interface AppComponent : AndroidInjector<TestApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}