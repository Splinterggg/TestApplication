package cvdevelopers.takehome.api

import javax.inject.Inject


class ClientsDataSource @Inject constructor(
    private val randomUserApiEndpoint: RandomUserApiEndpoint
): BaseDataSource() {

    suspend fun getClients() = getResult { randomUserApiEndpoint.getClient() }
}