package cu.infocap.gobmun.di.component

import cu.infocap.gobmun.GobMunApp
import cu.infocap.gobmun.di.module.ActivityModule
import cu.infocap.gobmun.di.module.AppModule
import cu.infocap.gobmun.di.module.NetModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityModule::class,
    AppModule::class,
    NetModule::class
])
interface AppComponent : AndroidInjector<GobMunApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: GobMunApp): Builder?

        fun build(): AppComponent
    }

    override fun inject(instance: GobMunApp?)

}