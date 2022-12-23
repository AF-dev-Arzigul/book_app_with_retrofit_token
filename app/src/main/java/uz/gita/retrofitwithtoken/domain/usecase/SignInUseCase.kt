package uz.gita.retrofitwithtoken.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.retrofitwithtoken.data.source.remote.dto.request.AuthRequest.SignIn
import uz.gita.retrofitwithtoken.data.source.remote.dto.response.AuthResponse
import uz.gita.retrofitwithtoken.utils.ResultData

interface SignInUseCase {
    fun signIn(signInRequest: SignIn): Flow<ResultData<AuthResponse.SignIn>>
    fun checkSignInput(password: String, phone: String): Pair<Boolean, String>
}