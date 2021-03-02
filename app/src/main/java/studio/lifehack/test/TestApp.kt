package studio.lifehack.test

import coil.ImageLoader
import coil.ImageLoaderFactory
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import studio.lifehack.test.di.component.DaggerAppComponent
import javax.inject.Inject


class TestApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}