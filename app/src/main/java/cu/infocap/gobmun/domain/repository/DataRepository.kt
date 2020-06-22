package cu.infocap.gobmun.domain.repository

import cu.infocap.gobmun.data.remote.Api
import cu.infocap.gobmun.data.remote.Resource
import cu.infocap.gobmun.data.remote.Status
import cu.infocap.gobmun.domain.model.Entity
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DataRepository @Inject constructor(private val api: Api) {

    fun fetchEntity(): Flowable<Resource<List<Entity>>> {
        return api.fetchEntity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { Resource(Status.SUCCESS, it, null) }
                .onErrorReturn { Resource(Status.ERROR, null, it) }
    }

}