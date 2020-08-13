package cu.infocap.gobmun.di.module

import cu.infocap.gobmun.ui.MainActivity
import cu.infocap.gobmun.ui.detail.DetailActivity
import cu.infocap.gobmun.ui.home.BottomActivity
import cu.infocap.gobmun.ui.welcome.WelcomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    fun contributeBottomActivity(): BottomActivity

    @ContributesAndroidInjector()
    fun contributeWelcomeActivity(): WelcomeActivity

    @ContributesAndroidInjector()
    fun contributeDetailActivity(): DetailActivity

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    fun contributeMainActivity(): MainActivity

}