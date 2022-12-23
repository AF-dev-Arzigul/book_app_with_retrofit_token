package uz.gita.retrofitwithtoken.presentation.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import uz.gita.retrofitwithtoken.data.source.local.shp.LocalStorage
import uz.gita.retrofitwithtoken.data.source.remote.dto.request.AuthRequest
import uz.gita.retrofitwithtoken.domain.usecase.SignInUseCase
import uz.gita.retrofitwithtoken.navigation.Navigator
import uz.gita.retrofitwithtoken.presentation.screen.loginScreen.SignInScreenDirections
import uz.gita.retrofitwithtoken.presentation.screen.loginScreen.SignInViewModel
import uz.gita.retrofitwithtoken.utils.LoadingType
import uz.gita.retrofitwithtoken.utils.ResultData
import javax.inject.Inject


@HiltViewModel
class SignInViewModelImpl @Inject constructor(
    private val navigator: Navigator,
    private val useCase: SignInUseCase,
    private val storage: LocalStorage
) : SignInViewModel, ViewModel() {
    override val failureFlow: MutableSharedFlow<String> = MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)
    override val successFlow: MutableSharedFlow<Unit> = MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)
    override val loading: MutableSharedFlow<LoadingType> = MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)
    override val hasConnection: MutableSharedFlow<Boolean> = MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)
    override val isValidFlow: MutableSharedFlow<Boolean> = MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)

    override fun signIn(password: String, phone: String) {
        viewModelScope.launch {
            val checkSignInput = useCase.checkSignInput(password, phone)
            if (checkSignInput.first) {
                isValidFlow.emit(true)
                useCase.signIn(AuthRequest.SignIn(phone, password)).collect { resultData ->
                    when (resultData) {
                        is ResultData.Failure -> {
                            failureFlow.emit(resultData.message)
                        }
                        is ResultData.HasConnection -> hasConnection.emit(resultData.state)
                        is ResultData.Loading -> loading.emit(resultData.state)
                        is ResultData.Success -> {
                            storage.token = resultData.data!!.token
                            successFlow.emit(Unit)
                        }
                    }
                }
            } else {
                isValidFlow.emit(false)
            }
        }
    }

    override fun openRegister() {
        viewModelScope.launch {
            navigator.navigateTo(SignInScreenDirections.actionLoginScreenToSignUpScreen())
        }
    }

    override fun openVerify() {
        viewModelScope.launch {
            navigator.navigateTo(SignInScreenDirections.actionLoginScreenToLoginVerifyScreen())
        }
    }

}