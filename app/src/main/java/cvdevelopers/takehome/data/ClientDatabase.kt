package cvdevelopers.takehome.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ClientTable::class], version = 1)
abstract class ClientDatabase : RoomDatabase() {

    abstract fun clientDao(): ClientDao

    companion object {
        @Volatile private var instance: ClientDatabase? = null

        fun getDatabase(context: Context): ClientDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, ClientDatabase::class.java, "clients")
                .fallbackToDestructiveMigration()
                .build()
    }
}