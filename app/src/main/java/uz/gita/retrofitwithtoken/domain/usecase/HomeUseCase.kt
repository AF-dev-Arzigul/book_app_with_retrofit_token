package uz.gita.retrofitwithtoken.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.retrofitwithtoken.data.source.remote.dto.request.BookRequest
import uz.gita.retrofitwithtoken.data.source.remote.dto.response.BookResponse
import uz.gita.retrofitwithtoken.data.source.remote.dto.response.MessageResponse
import uz.gita.retrofitwithtoken.data.source.remote.dto.response.UserResponse
import uz.gita.retrofitwithtoken.utils.ResultData

interface HomeUseCase {
    fun getBooks(): Flow<ResultData<List<BookResponse.OwnerBook>>>
    fun addBook(addBook: BookRequest.AddBook): Flow<ResultData<BookResponse.OwnerBook>>
    fun updateBook(updateBook: BookRequest.UpdateBook): Flow<ResultData<BookResponse.OwnerBook>>
    fun deleteBook(deleteBook: BookRequest.DeleteBook): Flow<ResultData<MessageResponse>>
    fun changeFav(changeFavOfBook: BookRequest.ChangeFavOfBook): Flow<ResultData<MessageResponse>>
    fun getUsers(): Flow<ResultData<List<UserResponse>>>
}