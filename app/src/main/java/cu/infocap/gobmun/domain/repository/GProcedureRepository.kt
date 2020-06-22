package cu.infocap.gobmun.domain.repository

import cu.infocap.gobmun.data.remote.Api
import cu.infocap.gobmun.domain.model.BaseData
import cu.infocap.gobmun.domain.model.Data
import io.reactivex.Observable
import javax.inject.Inject

class GProcedureRepository @Inject constructor(private val api: Api){

    fun fetchServiceByType(entityId: String) : Observable<List<Data>>{
        return api.fetchTransact(entityId)
    }
}