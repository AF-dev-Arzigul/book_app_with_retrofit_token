package uz.gita.retrofitwithtoken.data.source.remote.dto.response

sealed class AuthResponse {

    data class SignIn(
        val token: String
    )

    data class SignInVerify(
        val token: String
    )

    data class SignUp(
        val token: String
    )

    data class SignUpVerify(
        val token: String
    )

    data class SignInError(
        val error: String
    )

}