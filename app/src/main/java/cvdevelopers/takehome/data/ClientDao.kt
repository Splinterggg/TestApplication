package cvdevelopers.takehome.data

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ClientDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertClients(clients: List<ClientTable>)

    @Query("SELECT * FROM client_table")
    fun getClients(): LiveData<List<ClientTable>>

    @Query("DELETE FROM client_table")
    fun deleteClients()

    @Transaction
    fun makeInsertion(clients: List<ClientTable>) {
        deleteClients()
        insertClients(clients)
    }
}