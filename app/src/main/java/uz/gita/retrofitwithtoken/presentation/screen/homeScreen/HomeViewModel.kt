package uz.gita.retrofitwithtoken.presentation.screen.homeScreen

import kotlinx.coroutines.flow.MutableSharedFlow
import uz.gita.retrofitwithtoken.data.source.remote.dto.request.BookRequest
import uz.gita.retrofitwithtoken.data.source.remote.dto.response.BookResponse
import uz.gita.retrofitwithtoken.data.source.remote.dto.response.UserResponse
import uz.gita.retrofitwithtoken.presentation.viewModel.BaseViewModel

interface HomeViewModel : BaseViewModel {

    val bookList: MutableSharedFlow<List<BookResponse.OwnerBook>>
    val favBookList: MutableSharedFlow<List<BookResponse.OwnerBook>>
    val userList: MutableSharedFlow<List<UserResponse>>

    fun getBooks()
    fun addBook(addBook: BookRequest.AddBook)
    fun updateBook(updateBook: BookRequest.UpdateBook)
    fun deleteBook(deleteBook: BookRequest.DeleteBook)
    fun changeFav(changeFavOfBook: BookRequest.ChangeFavOfBook)
    fun getUsers()
    fun getFavBooks()
}