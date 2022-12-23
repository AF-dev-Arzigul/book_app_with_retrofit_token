package uz.gita.retrofitwithtoken.presentation.viewModel

import kotlinx.coroutines.flow.MutableSharedFlow
import uz.gita.retrofitwithtoken.utils.LoadingType

interface BaseViewModel {
    val failureFlow: MutableSharedFlow<String>
    val successFlow: MutableSharedFlow<Unit>
    val loading: MutableSharedFlow<LoadingType>
    val hasConnection: MutableSharedFlow<Boolean>
    val isValidFlow: MutableSharedFlow<Boolean>
}