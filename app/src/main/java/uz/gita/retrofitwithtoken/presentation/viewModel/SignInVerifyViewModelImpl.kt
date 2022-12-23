package uz.gita.retrofitwithtoken.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.retrofitwithtoken.domain.usecase.SignInUseCase
import uz.gita.retrofitwithtoken.domain.usecase.SignInVerifyUseCase
import uz.gita.retrofitwithtoken.navigation.Navigator
import uz.gita.retrofitwithtoken.presentation.screen.loginVerifyScreen.SignInVerifyScreenDirections
import uz.gita.retrofitwithtoken.presentation.screen.loginVerifyScreen.SignInVerifyViewModel
import uz.gita.retrofitwithtoken.utils.LoadingType
import uz.gita.retrofitwithtoken.utils.ResultData
import javax.inject.Inject

@HiltViewModel
class SignInVerifyViewModelImpl @Inject constructor(
    private val navigator: Navigator,
    private val useCase: SignInVerifyUseCase
) : SignInVerifyViewModel, ViewModel() {
    override val failureFlow: MutableSharedFlow<String> = MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)
    override val successFlow: MutableSharedFlow<Unit> = MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)
    override val loading: MutableSharedFlow<LoadingType> = MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)
    override val hasConnection: MutableSharedFlow<Boolean> = MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)
    override val isValidFlow: MutableSharedFlow<Boolean> = MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)

    override fun verifyCode(code: String) {
        useCase.verify(code).onEach {
            when (it) {
                is ResultData.Failure -> failureFlow.emit(it.message)
                is ResultData.HasConnection -> TODO()
                is ResultData.Loading -> TODO()
                is ResultData.Success -> navigator.navigateTo(SignInVerifyScreenDirections.actionLoginVerifyScreenToBaseScreen())
            }
        }.launchIn(viewModelScope)

    }


}
