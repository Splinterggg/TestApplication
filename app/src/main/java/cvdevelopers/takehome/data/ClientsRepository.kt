package cvdevelopers.takehome.data

import cvdevelopers.takehome.utils.performGetOperation
import cvdevelopers.takehome.utils.performUpdateOperation
import cvdevelopers.takehome.api.ClientsDataSource
import cvdevelopers.takehome.utils.toTableItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClientsRepository @Inject constructor(
    private val clientsDataSource: ClientsDataSource,
    private val dao: ClientDao
) {

    fun getClients() = performGetOperation(
        databaseQuery = { dao.getClients() },
        networkCall = { clientsDataSource.getClients() },
        saveCallResult = {
            dao.deleteClients()
            dao.insertClients(it.results.map { it.toTableItem() })
        }
    )

    fun refreshClients() = performUpdateOperation(
        databaseQuery = { dao.getClients() },
        networkCall = { clientsDataSource.getClients() },
        saveCallResult = {
            dao.deleteClients()
            dao.insertClients(it.results.map { it.toTableItem() })
        }
    )
}