package uz.gita.retrofitwithtoken.data.source.remote.dto.response

sealed class BookResponse {

    data class OwnerBook(
        val id: Int,
        val title: String,
        val author: String,
        val description: String,
        val pageCount: Int,
        val fav: Boolean
    )

    data class OthersBook(
        val id: String,
        val title: String,
        val author: String,
        val description: String,
        val pageCount: String,
        val fav: String,
        val isLike: String,
        val likeCount: String,
        val disLikeCount: String
    )

}