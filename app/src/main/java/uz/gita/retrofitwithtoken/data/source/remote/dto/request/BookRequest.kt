package uz.gita.retrofitwithtoken.data.source.remote.dto.request

sealed class BookRequest {

    data class AddBook(
        val title: String,
        val author: String,
        val description: String,
        val pageCount: Int
    )

    data class UpdateBook(
        val id: Int,
        val title: String,
        val author: String,
        val description: String,
        val pageCount: Int
    )

    data class DeleteBook(
        val bookId: Int
    )

    data class ChangeFavOfBook(
        val bookId: Int
    )

    data class LikeDislikeBook(
        val bookId: Int,
        val isLike: Boolean
    )

}