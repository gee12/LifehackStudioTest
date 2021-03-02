package studio.lifehack.test.di.module

import studio.lifehack.test.di.scope.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector
import studio.lifehack.test.ui.company.CompaniesFragment
import studio.lifehack.test.ui.details.DetailsFragment

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun contributeCompanyFragment(): CompaniesFragment

    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun contributeDetailsFragment(): DetailsFragment
}
