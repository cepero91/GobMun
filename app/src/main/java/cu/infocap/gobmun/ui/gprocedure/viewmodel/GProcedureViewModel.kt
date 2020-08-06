package cu.infocap.gobmun.ui.gprocedure.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cu.infocap.gobmun.data.remote.Api
import cu.infocap.gobmun.data.remote.Resource
import cu.infocap.gobmun.data.remote.Status
import cu.infocap.gobmun.domain.model.BaseData
import cu.infocap.gobmun.domain.model.Data
import cu.infocap.gobmun.domain.repository.GProcedureRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GProcedureViewModel @Inject constructor(
        private val procedureRepository: GProcedureRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val gprocedureList: MutableLiveData<Resource<List<Data>>> = MutableLiveData()

    fun loadProcedure(entityId: String) {
        compositeDisposable.add(procedureRepository.fetchServiceByType(entityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    gprocedureList.value = Resource(Status.SUCCESS, it, null)
                }, {
                    gprocedureList.value = Resource(Status.ERROR, null, it)
                }))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }


}