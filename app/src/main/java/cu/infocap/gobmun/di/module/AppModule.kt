package cu.infocap.gobmun.di.module

import cu.infocap.gobmun.GobMunApp
import cu.infocap.gobmun.data.remote.Api
import cu.infocap.gobmun.domain.repository.DataRepository
import cu.infocap.gobmun.domain.repository.GProcedureRepository
import cu.infocap.gobmun.domain.repository.GServiceRepository
import cu.infocap.gobmun.util.NetworkHelper
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [(ViewModelModule::class)])
class AppModule {

    @Provides
    @Singleton
    fun provideApi(retorfit: Retrofit): Api {
        return retorfit.create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideGProcedureRepository(api: Api): GProcedureRepository {
        return GProcedureRepository(api)
    }

    @Provides
    @Singleton
    fun provideGServiceRepository(api: Api): GServiceRepository {
        return GServiceRepository(api)
    }

    @Provides
    @Singleton
    fun provideDataRepository(api: Api): DataRepository {
        return DataRepository(api)
    }

    @Provides
    @Singleton
    fun provideNetworkHelper(app: GobMunApp): NetworkHelper {
        return NetworkHelper(app.applicationContext)
    }

}