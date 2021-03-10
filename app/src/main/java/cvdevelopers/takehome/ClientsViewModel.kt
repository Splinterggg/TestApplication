package cvdevelopers.takehome

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import cvdevelopers.takehome.data.ClientTable
import cvdevelopers.takehome.data.ClientsRepository
import cvdevelopers.takehome.utils.RefreshableLiveData
import cvdevelopers.takehome.utils.Resource

class ClientsViewModel @ViewModelInject constructor(private val repository: ClientsRepository) :
    ViewModel() {

    private val _clients = RefreshableLiveData { repository.getClients() }
    val clients: LiveData<Resource<List<ClientTable>>> = _clients

    fun refresh() {
        _clients.refresh(repository.refreshClients())
    }

}