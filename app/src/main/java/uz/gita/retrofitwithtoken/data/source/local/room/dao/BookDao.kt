package uz.gita.retrofitwithtoken.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import uz.gita.retrofitwithtoken.data.source.local.room.entity.BookData


@Dao
interface BookDao {

    @Insert
    suspend fun insert(vararg book: BookData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bookData: List<BookData>)

    @Delete
    suspend fun delete(vararg bookData: BookData)

    @Delete
    suspend fun delete(bookData: List<BookData>)

    @Query("SELECT * FROM bookdata WHERE id = :id")
    suspend fun getContactData(id: Long): BookData

    @Update
    suspend fun update(vararg bookData: BookData)

    @Update
    suspend fun update(bookData: List<BookData>)

    @Query("SELECT * FROM bookdata WHERE status != 3")
    fun getAllContacts(): Flow<List<BookData>>

    @Query("SELECT * FROM bookdata WHERE status != 0")
    suspend fun getModifiedContacts(): List<BookData>

    @Query("DELETE FROM bookdata")
    suspend fun deleteAll()

    @Transaction
    suspend fun updateAllContact(newList: List<BookData>) {
        deleteAll()
        insert(newList)
    }

}