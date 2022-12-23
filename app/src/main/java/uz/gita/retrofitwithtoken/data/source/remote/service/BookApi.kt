package uz.gita.retrofitwithtoken.data.source.remote.service

import retrofit2.Response
import retrofit2.http.*
import uz.gita.retrofitwithtoken.data.source.remote.dto.request.BookRequest
import uz.gita.retrofitwithtoken.data.source.remote.dto.response.BookResponse
import uz.gita.retrofitwithtoken.data.source.remote.dto.response.MessageResponse
import uz.gita.retrofitwithtoken.data.source.remote.dto.response.UserResponse

interface BookApi {

    @GET("books")
    suspend fun getAllBooks(@Header("Authorization") token: String): Response<List<BookResponse.OwnerBook>>

    @POST("book")
    suspend fun addBook(@Header("Authorization") token: String, @Body addBook: BookRequest.AddBook): Response<BookResponse.OwnerBook>

    @DELETE("book")
    suspend fun deleteBook(@Header("Authorization") token: String, @Body deleteBook: BookRequest.DeleteBook): Response<MessageResponse>

    @PUT("book")
    suspend fun updateBook(@Header("Authorization") token: String, @Body updateBook: BookRequest.UpdateBook): Response<BookResponse.OwnerBook>

    @POST("book/change-fav")
    suspend fun changeFav(@Header("Authorization") token: String, @Body changeFavOfBook: BookRequest.ChangeFavOfBook): Response<MessageResponse>

    @GET("books/users")
    suspend fun getUsers(@Header("Authorization") token: String): Response<List<UserResponse>>

}