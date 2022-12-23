package uz.gita.retrofitwithtoken.presentation.viewModel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uz.gita.retrofitwithtoken.data.source.remote.dto.request.BookRequest
import uz.gita.retrofitwithtoken.data.source.remote.dto.response.BookResponse
import uz.gita.retrofitwithtoken.data.source.remote.dto.response.UserResponse
import uz.gita.retrofitwithtoken.domain.usecase.HomeUseCase
import uz.gita.retrofitwithtoken.navigation.Navigator
import uz.gita.retrofitwithtoken.presentation.screen.homeScreen.HomeViewModel
import uz.gita.retrofitwithtoken.utils.LoadingType
import uz.gita.retrofitwithtoken.utils.ResultData
import javax.inject.Inject


@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val navigator: Navigator,
    private val homeUseCase: HomeUseCase
) : HomeViewModel, ViewModel() {
    override val failureFlow: MutableSharedFlow<String> = MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)
    override val loading: MutableSharedFlow<LoadingType> = MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)
    override val successFlow: MutableSharedFlow<Unit> = MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)
    override val hasConnection: MutableSharedFlow<Boolean> = MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)
    override val isValidFlow: MutableSharedFlow<Boolean> = MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)
    override val bookList: MutableSharedFlow<List<BookResponse.OwnerBook>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)
    override val favBookList: MutableSharedFlow<List<BookResponse.OwnerBook>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)
    override val userList: MutableSharedFlow<List<UserResponse>> = MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)

    init {
        viewModelScope.launch {
            getFavBooks()
        }
    }

    override fun getBooks() {
        viewModelScope.launch {
            homeUseCase.getBooks().collectLatest {
                when (it) {
                    is ResultData.Failure -> failureFlow.emit("Error")
                    is ResultData.HasConnection -> TODO()
                    is ResultData.Loading -> TODO()
                    is ResultData.Success -> it.data?.let { it1 -> bookList.emit(it1) }
                }
            }
        }
    }

    override fun addBook(addBook: BookRequest.AddBook) {
        homeUseCase.addBook(addBook).onEach {
            when (it) {
                is ResultData.Failure -> failureFlow.emit("Error")
                is ResultData.HasConnection -> TODO()
                is ResultData.Loading -> TODO()
                is ResultData.Success -> {

                }
            }
        }.launchIn(viewModelScope)
    }

    override fun updateBook(updateBook: BookRequest.UpdateBook) {
        homeUseCase.updateBook(updateBook).onEach {
            when (it) {
                is ResultData.Failure -> failureFlow.emit("Error")
                is ResultData.HasConnection -> TODO()
                is ResultData.Loading -> TODO()
                is ResultData.Success -> {}
            }
        }.launchIn(viewModelScope)
    }

    override fun deleteBook(deleteBook: BookRequest.DeleteBook) {
        homeUseCase.deleteBook(deleteBook).onEach {
            when (it) {
                is ResultData.Failure -> failureFlow.emit("Error")
                is ResultData.HasConnection -> TODO()
                is ResultData.Loading -> TODO()
                is ResultData.Success -> {}
            }
        }.launchIn(viewModelScope)
    }

    override fun changeFav(changeFavOfBook: BookRequest.ChangeFavOfBook) {
        homeUseCase.changeFav(changeFavOfBook).onEach {
            when (it) {
                is ResultData.Failure -> failureFlow.emit("Error")
                is ResultData.HasConnection -> TODO()
                is ResultData.Loading -> TODO()
                is ResultData.Success -> {
                    getBooks()
                    getFavBooks()
//                    val book = viewModelScope.async { getBooks() }
//                    val fav = viewModelScope.async { getFavBooks() }
//                    book.await()
//                    fav.await()
                }
            }
        }.launchIn(viewModelScope)
    }

    override fun getUsers() {
        homeUseCase.getUsers().onEach {
            when (it) {
                is ResultData.Failure -> failureFlow.emit("Error")
                is ResultData.HasConnection -> TODO()
                is ResultData.Loading -> TODO()
                is ResultData.Success -> it.data?.let { it1 -> userList.emit(it1) }
            }
        }.launchIn(viewModelScope)
    }

    override fun getFavBooks() {
        viewModelScope.launch {
            homeUseCase.getBooks().collectLatest { item ->
                when (item) {
                    is ResultData.Failure -> failureFlow.emit("Error")
                    is ResultData.HasConnection -> TODO()
                    is ResultData.Loading -> TODO()
                    is ResultData.Success -> {
                        item.data?.filter { list ->
                            list.fav
                        }?.let { likedList ->
                            Log.d("xxxx", "filter size VM ${likedList.size} data $likedList")
                            favBookList.emit(likedList)
                        }
                    }
                }
            }
        }

    }
}