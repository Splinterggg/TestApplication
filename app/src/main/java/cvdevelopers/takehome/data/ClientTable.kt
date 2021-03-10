package cvdevelopers.takehome.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import cvdevelopers.takehome.models.Picture
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "client_table")
data class ClientTable(
    @PrimaryKey val email: String,
    val IdName: String,
    val IdValue: String,
    val firstName: String,
    val lastName: String,
    val clientTitle: String,
    val largePicture: String,
    val mediumPicture: String,
    val pictureThumbnail: String
)