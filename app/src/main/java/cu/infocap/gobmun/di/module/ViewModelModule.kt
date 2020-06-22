package cu.infocap.gobmun.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import cu.infocap.gobmun.commons.ViewModelFactory
import cu.infocap.gobmun.di.key.ViewModelKey
import cu.infocap.gobmun.ui.gprocedure.viewmodel.GProcedureViewModel
import cu.infocap.gobmun.ui.gservice.viewmodel.GServiceViewModel
import cu.infocap.gobmun.ui.welcome.WelcomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(GProcedureViewModel::class)
    fun bindGProcedureViewModel(gProcedureViewModel: GProcedureViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GServiceViewModel::class)
    fun bindGServiceViewModel(gServiceViewModel: GServiceViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WelcomeViewModel::class)
    fun bindWelcomeViewModel(welcomeViewModel: WelcomeViewModel) : ViewModel

    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

}