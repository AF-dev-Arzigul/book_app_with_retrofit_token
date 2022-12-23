package uz.gita.retrofitwithtoken.data.source.remote.service

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import uz.gita.retrofitwithtoken.data.source.remote.dto.request.AuthRequest.SignUp
import uz.gita.retrofitwithtoken.data.source.remote.dto.request.AuthRequest.SignIn
import uz.gita.retrofitwithtoken.data.source.remote.dto.request.AuthRequest.Verify
import uz.gita.retrofitwithtoken.data.source.remote.dto.response.AuthResponse

interface AuthApi {

    @POST("auth/sign-up")
    suspend fun signUp(@Body data: SignUp): Response<AuthResponse.SignUp>

    @POST("auth/sign-up/verify")
    suspend fun signUpVerify(@Header("authorization") token: String, @Body code: Verify): Response<AuthResponse.SignUpVerify>

    @POST("auth/sign-in")
    suspend fun signIn(@Body data: SignIn): Response<AuthResponse.SignIn>

    @POST("auth/sign-in/verify")
    suspend fun signInVerify(@Header("authorization") token: String, @Body code: Verify): Response<AuthResponse.SignInVerify>

}