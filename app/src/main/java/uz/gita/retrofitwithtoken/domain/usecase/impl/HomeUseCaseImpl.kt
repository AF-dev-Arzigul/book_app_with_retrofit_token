package uz.gita.retrofitwithtoken.domain.usecase.impl


import kotlinx.coroutines.flow.Flow
import uz.gita.retrofitwithtoken.data.source.remote.dto.request.BookRequest
import uz.gita.retrofitwithtoken.data.source.remote.dto.response.BookResponse
import uz.gita.retrofitwithtoken.data.source.remote.dto.response.MessageResponse
import uz.gita.retrofitwithtoken.data.source.remote.dto.response.UserResponse
import uz.gita.retrofitwithtoken.domain.repository.BookRepository
import uz.gita.retrofitwithtoken.domain.usecase.HomeUseCase
import uz.gita.retrofitwithtoken.utils.ResultData
import javax.inject.Inject

class HomeUseCaseImpl @Inject constructor(
    private val repo: BookRepository
) : HomeUseCase {
    override fun getBooks(): Flow<ResultData<List<BookResponse.OwnerBook>>> {
        return repo.getBooks()
    }

    override fun addBook(addBook: BookRequest.AddBook): Flow<ResultData<BookResponse.OwnerBook>> {
        return repo.addBook(addBook)
    }

    override fun updateBook(updateBook: BookRequest.UpdateBook): Flow<ResultData<BookResponse.OwnerBook>> {
        return repo.updateBook(updateBook)
    }

    override fun deleteBook(deleteBook: BookRequest.DeleteBook): Flow<ResultData<MessageResponse>> {
        return repo.deleteBook(deleteBook)
    }

    override fun changeFav(changeFavOfBook: BookRequest.ChangeFavOfBook): Flow<ResultData<MessageResponse>> {
        return repo.changeFav(changeFavOfBook)
    }

    override fun getUsers(): Flow<ResultData<List<UserResponse>>> {
        return repo.getUser()
    }
}

