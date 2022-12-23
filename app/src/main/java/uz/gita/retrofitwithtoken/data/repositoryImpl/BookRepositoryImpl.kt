package uz.gita.retrofitwithtoken.data.repositoryImpl

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.retrofitwithtoken.data.source.local.shp.LocalStorage
import uz.gita.retrofitwithtoken.data.source.remote.dto.request.BookRequest
import uz.gita.retrofitwithtoken.data.source.remote.dto.response.BookResponse
import uz.gita.retrofitwithtoken.data.source.remote.dto.response.MessageResponse
import uz.gita.retrofitwithtoken.data.source.remote.dto.response.UserResponse
import uz.gita.retrofitwithtoken.data.source.remote.service.BookApi
import uz.gita.retrofitwithtoken.domain.repository.BookRepository
import uz.gita.retrofitwithtoken.utils.ResultData
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val storage: LocalStorage,
    private val bookApi: BookApi,
) : BookRepository {
    override fun getBooks(): Flow<ResultData<List<BookResponse.OwnerBook>>> = flow<ResultData<List<BookResponse.OwnerBook>>> {
        val response = bookApi.getAllBooks("Bearer ${storage.token}")

        if (response.isSuccessful) {
            response.body()?.let {
                emit(ResultData.Success(it))
            }
        } else {
            emit(ResultData.Failure("Xato"))
        }
    }.catch { error ->
        error.message?.let {
            emit(ResultData.Failure(it))
        }
    }.flowOn(Dispatchers.IO)

    override fun addBook(addBook: BookRequest.AddBook): Flow<ResultData<BookResponse.OwnerBook>> = flow<ResultData<BookResponse.OwnerBook>> {
        val response = bookApi.addBook("Bearer ${storage.token}", addBook)
        if (response.isSuccessful) {
            response.body()?.let {
                emit(ResultData.Success(it))
            }
        } else {
            emit(ResultData.Failure("Something went wrong"))
        }
    }.catch { error ->
        error.message?.let {
            emit(ResultData.Failure(it))
        }
    }.flowOn(Dispatchers.IO)

    override fun updateBook(updateBook: BookRequest.UpdateBook): Flow<ResultData<BookResponse.OwnerBook>> = flow<ResultData<BookResponse.OwnerBook>> {
        val response = bookApi.updateBook("Bearer ${storage.token}", updateBook)
        if (response.isSuccessful) {
            response.body()?.let {
                emit(ResultData.Success(it))
            }
        } else {
            emit(ResultData.Failure("Something went wrong"))
        }
    }.catch { error ->
        error.message?.let {
            emit(ResultData.Failure(it))
        }
    }.flowOn(Dispatchers.IO)

    override fun deleteBook(deleteBook: BookRequest.DeleteBook): Flow<ResultData<MessageResponse>> = flow<ResultData<MessageResponse>> {
        val response = bookApi.deleteBook("Bearer ${storage.token}", deleteBook)
        if (response.isSuccessful) {
            response.body()?.let {
                emit(ResultData.Success(it))
            }
        } else {
            emit(ResultData.Failure("Something went wrong"))
        }
    }.catch { error ->
        error.message?.let {
            emit(ResultData.Failure(it))
        }
    }.flowOn(Dispatchers.IO)

    override fun changeFav(changeFavOfBook: BookRequest.ChangeFavOfBook): Flow<ResultData<MessageResponse>> = flow<ResultData<MessageResponse>> {
        val response = bookApi.changeFav("Bearer ${storage.token}", changeFavOfBook)
        if (response.isSuccessful) {
            response.body()?.let {
                emit(ResultData.Success(it))
            }
        } else {
            emit(ResultData.Failure("Something went wrong"))
        }
    }.catch { error ->
        error.message?.let {
            emit(ResultData.Failure(it))
        }
    }.flowOn(Dispatchers.IO)

    override fun getUser(): Flow<ResultData<List<UserResponse>>> = flow<ResultData<List<UserResponse>>> {
        val response = bookApi.getUsers("Bearer ${storage.token}")

        if (response.isSuccessful) {
            response.body()?.let {
                emit(ResultData.Success(it))
            }
        } else {
            emit(ResultData.Failure("Something went wrong"))

        }
    }.catch { error ->

        error.message?.let {
            emit(ResultData.Failure(it))
        }

    }.flowOn(Dispatchers.IO)

}