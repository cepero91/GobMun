package cu.infocap.gobmun.data.remote

import cu.infocap.gobmun.domain.model.BaseData
import cu.infocap.gobmun.domain.model.Data
import cu.infocap.gobmun.domain.model.Entity
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface Api {

    @GET("transact/by-entity/")
    fun fetchTransact(@Query("entityId") entityId: String) : Observable<List<Data>>

    @GET("services/by-entity/")
    fun fetchServices(@Query("entityId") entityId: String) : Observable<List<Data>>

    @GET("entity/?provinceId=1")
    fun fetchEntity() : Flowable<List<Entity>>


}