package cvdevelopers.takehome.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

class RefreshableLiveData<T>(
    private val source: () -> LiveData<T>
) : MediatorLiveData<T>() {

    private var liveData = source()

    init {
        this.addSource(liveData, ::observer)
    }

    private fun observer(data: T) {
        value = data
    }

    fun refresh(newSource :LiveData<T>) {
        this.removeSource(liveData)
        liveData = newSource
        this.addSource(liveData, ::observer)
    }
}