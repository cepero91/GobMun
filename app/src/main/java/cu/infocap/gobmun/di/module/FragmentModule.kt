package cu.infocap.gobmun.di.module

import cu.infocap.gobmun.base.BaseFragment
import cu.infocap.gobmun.ui.aboutus.AboutServiceDetailFragment
import cu.infocap.gobmun.ui.detail.DetailFragment
import cu.infocap.gobmun.ui.gprocedure.GProcedureFragment
import cu.infocap.gobmun.ui.gservice.GServiceFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @ContributesAndroidInjector
    fun contributeBaseFragment(): BaseFragment

    @ContributesAndroidInjector
    fun contributeGProcedureFragment(): GProcedureFragment

    @ContributesAndroidInjector
    fun contributeGServiceFragment(): GServiceFragment

    @ContributesAndroidInjector
    fun contributeDetailFragment(): DetailFragment

    @ContributesAndroidInjector
    fun contributeAboutServiceDetailFragment(): AboutServiceDetailFragment

}