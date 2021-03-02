package studio.lifehack.test.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import studio.lifehack.test.di.module.FragmentBuildersModule
import studio.lifehack.test.di.scope.ActivityScope
import studio.lifehack.test.ui.MainActivity

@Suppress("unused")
@Module
abstract class MainActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}