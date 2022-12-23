package uz.gita.retrofitwithtoken.data.source.local.room.dao

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [])
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

}