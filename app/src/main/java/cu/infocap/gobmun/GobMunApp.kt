package cu.infocap.gobmun

import cu.infocap.gobmun.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class GobMunApp: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out GobMunApp> {
        return DaggerAppComponent.builder().application(this)!!.build()
    }
}