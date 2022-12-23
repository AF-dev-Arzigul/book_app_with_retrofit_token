package uz.gita.retrofitwithtoken.presentation.viewModel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import uz.gita.retrofitwithtoken.data.source.remote.dto.request.AuthRequest
import uz.gita.retrofitwithtoken.presentation.screen.signUpScreen.SignUpViewModel
import uz.gita.retrofitwithtoken.utils.LoadingType
import javax.inject.Inject

@HiltViewModel
class SignUpViewModelImpl @Inject constructor() : SignUpViewModel, ViewModel() {
    override val failureFlow: MutableSharedFlow<String> = MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)
    override val successFlow: MutableSharedFlow<Unit> = MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)
    override val loading: MutableSharedFlow<LoadingType> = MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)
    override val hasConnection: MutableSharedFlow<Boolean> = MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)
    override val isValidFlow: MutableSharedFlow<Boolean> = MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)

    override fun openVerify() {
        TODO("Not yet implemented")
    }

    override fun signUp(signUpRequest: AuthRequest.SignUp, rePassword: String) {
        TODO("Not yet implemented")
    }

}
