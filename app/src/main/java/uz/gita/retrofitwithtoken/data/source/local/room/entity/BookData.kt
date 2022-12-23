package uz.gita.retrofitwithtoken.data.source.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookData(
    @PrimaryKey(autoGenerate = true)
    val localId: Long,
    val id: Long,
    val title:String,
    val author: String,
    val description: String,
    val pageCount: Int,
    val favourite: Boolean,
    val status: Status = Status.DEFAULT
)