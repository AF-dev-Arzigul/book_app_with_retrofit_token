package uz.gita.retrofitwithtoken.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.retrofitwithtoken.data.repositoryImpl.AuthRepositoryImpl
import uz.gita.retrofitwithtoken.data.repositoryImpl.BookRepositoryImpl
import uz.gita.retrofitwithtoken.domain.repository.AuthRepository
import uz.gita.retrofitwithtoken.domain.repository.BookRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun authRepository(impl: AuthRepositoryImpl): AuthRepository

    @[Binds Singleton]
    fun bookRepository(bookRepositoryImpl: BookRepositoryImpl): BookRepository

}