package uz.gita.retrofitwithtoken.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.retrofitwithtoken.data.source.remote.dto.response.AuthResponse
import uz.gita.retrofitwithtoken.domain.repository.AuthRepository
import uz.gita.retrofitwithtoken.domain.usecase.SignInVerifyUseCase
import uz.gita.retrofitwithtoken.utils.ResultData
import javax.inject.Inject

class SignInVerifyUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : SignInVerifyUseCase {

    override fun verify(code: String): Flow<ResultData<AuthResponse.SignInVerify>> {
        return authRepository.signInVerify(code)
    }

}