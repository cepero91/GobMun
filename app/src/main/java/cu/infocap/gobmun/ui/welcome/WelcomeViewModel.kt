package cu.infocap.gobmun.ui.welcome

import androidx.lifecycle.*
import cu.infocap.gobmun.data.remote.Resource
import cu.infocap.gobmun.domain.model.Entity
import cu.infocap.gobmun.domain.repository.DataRepository

import javax.inject.Inject

class WelcomeViewModel @Inject constructor(
        private val dataRepository: DataRepository
) : ViewModel() {

    fun loadEntity(): LiveData<Resource<List<Entity>>> {
        return LiveDataReactiveStreams.fromPublisher(dataRepository.fetchEntity())
    }

}