package uz.gita.retrofitwithtoken.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.retrofitwithtoken.data.source.remote.dto.request.AuthRequest
import uz.gita.retrofitwithtoken.data.source.remote.dto.response.AuthResponse
import uz.gita.retrofitwithtoken.utils.ResultData

interface AuthRepository {
    fun signUp(signUpRequest: AuthRequest.SignUp): Flow<ResultData<AuthResponse.SignUp>>
    fun signUpVerify(verifyRequest: AuthRequest.Verify): Flow<ResultData<AuthResponse.SignUpVerify>>
    fun signIn(signInRequest: AuthRequest.SignIn): Flow<ResultData<AuthResponse.SignIn>>
    fun signInVerify(code: String): Flow<ResultData<AuthResponse.SignInVerify>>
}