package uz.gita.retrofitwithtoken.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.retrofitwithtoken.data.source.remote.dto.response.AuthResponse
import uz.gita.retrofitwithtoken.utils.ResultData


interface SignInVerifyUseCase {
    fun verify(code: String): Flow<ResultData<AuthResponse.SignInVerify>>
}