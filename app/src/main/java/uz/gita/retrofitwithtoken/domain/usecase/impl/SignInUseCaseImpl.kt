package uz.gita.retrofitwithtoken.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.retrofitwithtoken.data.source.remote.dto.request.AuthRequest
import uz.gita.retrofitwithtoken.data.source.remote.dto.response.AuthResponse
import uz.gita.retrofitwithtoken.domain.repository.AuthRepository
import uz.gita.retrofitwithtoken.domain.usecase.SignInUseCase
import uz.gita.retrofitwithtoken.utils.ResultData
import javax.inject.Inject

class SignInUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : SignInUseCase {

    override fun signIn(signInRequest: AuthRequest.SignIn): Flow<ResultData<AuthResponse.SignIn>> {
        return authRepository.signIn(signInRequest)
    }

    override fun checkSignInput(password: String, phone: String): Pair<Boolean, String> {
        return Pair(password.length >= 6 && phone.length == 13, phone)
    }

}