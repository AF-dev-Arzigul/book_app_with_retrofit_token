package uz.gita.retrofitwithtoken.presentation.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.retrofitwithtoken.data.source.local.shp.LocalStorage
import uz.gita.retrofitwithtoken.navigation.Navigator
import uz.gita.retrofitwithtoken.presentation.screen.splashScreen.SplashScreenDirections
import uz.gita.retrofitwithtoken.presentation.screen.splashScreen.SplashViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    private val navigator: Navigator,
    private val storage: LocalStorage
) : SplashViewModel, ViewModel() {

    override fun openLogInOrHomeScreen() {
        viewModelScope.launch {
            delay(1500)
            if (storage.isLogin) {
                navigator.navigateTo(SplashScreenDirections.actionSplashScreenToBaseScreen())
            } else {
                navigator.navigateTo(SplashScreenDirections.actionSplashScreenToLoginScreen())
            }
        }
    }

}