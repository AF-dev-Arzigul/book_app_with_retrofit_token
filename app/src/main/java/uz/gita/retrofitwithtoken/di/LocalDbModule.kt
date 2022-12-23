package uz.gita.retrofitwithtoken.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.retrofitwithtoken.data.source.local.room.dao.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDbModule {

    private val DB_NAME = "auth_book"

    @[Provides Singleton]
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()

    @[Provides Singleton]
    fun provideBookDao(appDatabase: AppDatabase) = appDatabase.bookDao()

}