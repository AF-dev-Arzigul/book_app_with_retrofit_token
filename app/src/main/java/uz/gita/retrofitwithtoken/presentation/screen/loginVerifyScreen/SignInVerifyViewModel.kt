package uz.gita.retrofitwithtoken.presentation.screen.loginVerifyScreen

import uz.gita.retrofitwithtoken.presentation.viewModel.BaseViewModel

interface SignInVerifyViewModel : BaseViewModel {
    fun verifyCode(code:String)
}