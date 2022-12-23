package uz.gita.retrofitwithtoken.presentation.viewModel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import uz.gita.retrofitwithtoken.presentation.screen.signUpVerifyScreen.SignUpVerifyViewModel
import uz.gita.retrofitwithtoken.utils.LoadingType
import javax.inject.Inject

@HiltViewModel
class SignUpVerifyViewModelImpl @Inject constructor() : SignUpVerifyViewModel, ViewModel() {
    override val failureFlow = MutableSharedFlow<String>()
    override val successFlow = MutableSharedFlow<Unit>()
    override val loading = MutableSharedFlow<LoadingType>()
    override val hasConnection = MutableSharedFlow<Boolean>()
    override val isValidFlow = MutableSharedFlow<Boolean>()


}