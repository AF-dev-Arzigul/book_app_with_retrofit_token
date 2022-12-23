package uz.gita.retrofitwithtoken.data.source.remote.dto.request

sealed class AuthRequest {
    data class SignUp(
        val phone: String,
        val password: String,
        val lastName: String,
        val firstName: String
    )

    data class SignIn(
        val phone: String,
        val password: String,
    )

    data class Verify(
        val code: String
    )

}
