package uz.gita.retrofitwithtoken.data.repositoryImpl


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.retrofitwithtoken.data.source.local.shp.LocalStorage
import uz.gita.retrofitwithtoken.data.source.remote.dto.request.AuthRequest
import uz.gita.retrofitwithtoken.data.source.remote.dto.response.AuthResponse
import uz.gita.retrofitwithtoken.data.source.remote.service.AuthApi
import uz.gita.retrofitwithtoken.domain.repository.AuthRepository
import uz.gita.retrofitwithtoken.utils.LoadingType
import uz.gita.retrofitwithtoken.utils.ResultData
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val storage: LocalStorage,
    private val authApi: AuthApi,
) : AuthRepository {

    override fun signUp(signUpRequest: AuthRequest.SignUp): Flow<ResultData<AuthResponse.SignUp>> = flow<ResultData<AuthResponse.SignUp>> {
        emit(ResultData.HasConnection(true))
        val response = authApi.signUp(signUpRequest)
        if (response.isSuccessful) {
            response.body()?.let {
                emit(ResultData.Success(it))
                emit(ResultData.Loading(LoadingType(fullScreen = true)))
            }
        } else {
            response.body()?.let {
                emit(ResultData.Failure(it.token))
                emit(ResultData.Loading(LoadingType(fullScreen = false)))
            }
        }

    }.catch { error ->
        error.message?.let {
            emit(ResultData.Failure(it))
            emit(ResultData.Loading(LoadingType(fullScreen = false)))
        }
    }.flowOn(Dispatchers.IO)

    override fun signUpVerify(verifyRequest: AuthRequest.Verify): Flow<ResultData<AuthResponse.SignUpVerify>> {
        TODO("Not yet implemented")
    }

    override fun signIn(signInRequest: AuthRequest.SignIn): Flow<ResultData<AuthResponse.SignIn>> = flow<ResultData<AuthResponse.SignIn>> {
        val response = authApi.signIn(signInRequest)
        if (response.isSuccessful) {
            response.body()?.let {
                emit(ResultData.Success(it))
                emit(ResultData.Loading(LoadingType(fullScreen = false)))
            }
        } else {
            emit(ResultData.Failure(authApi.signIn(signInRequest).message()))
        }
    }.catch { error ->
        error.message?.let {
            emit(ResultData.Failure(it))
            emit(ResultData.Loading(LoadingType(fullScreen = false)))
        }
    }.flowOn(Dispatchers.IO)

    override fun signInVerify(code: String): Flow<ResultData<AuthResponse.SignInVerify>> = flow<ResultData<AuthResponse.SignInVerify>> {
        val response = authApi.signInVerify("Bearer ${storage.token}", AuthRequest.Verify(code))
        if (response.isSuccessful) {
            response.body()?.let {
                storage.isLogin = true
                storage.token = it.token
                emit(ResultData.Success(it))
            }
        } else {
            emit(ResultData.Failure("Error"))
        }
    }.catch { error ->
        error.message?.let {
            emit(ResultData.Failure(it))
        }
    }.flowOn(Dispatchers.IO)

}