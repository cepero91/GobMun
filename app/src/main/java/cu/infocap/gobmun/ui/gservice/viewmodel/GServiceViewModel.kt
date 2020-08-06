package cu.infocap.gobmun.ui.gservice.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cu.infocap.gobmun.data.remote.Api
import cu.infocap.gobmun.data.remote.Resource
import cu.infocap.gobmun.data.remote.Status
import cu.infocap.gobmun.domain.model.BaseData
import cu.infocap.gobmun.domain.model.Data
import cu.infocap.gobmun.domain.repository.GProcedureRepository
import cu.infocap.gobmun.domain.repository.GServiceRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GServiceViewModel @Inject constructor(
        private val serviceRepository: GServiceRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val gserviceList: MutableLiveData<Resource<List<Data>>> = MutableLiveData()

    fun loadService(entityId: String) {
        compositeDisposable.add(serviceRepository.fetchServiceByType(entityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    gserviceList.value = Resource(Status.SUCCESS, it, null)
                }, {
                    gserviceList.value = Resource(Status.ERROR, null, it)
                }))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }


}