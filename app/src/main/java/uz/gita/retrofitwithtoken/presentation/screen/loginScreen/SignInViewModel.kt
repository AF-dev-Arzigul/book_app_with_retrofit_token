package uz.gita.retrofitwithtoken.presentation.screen.loginScreen

import uz.gita.retrofitwithtoken.presentation.viewModel.BaseViewModel


interface SignInViewModel: BaseViewModel {

    fun signIn(password: String, phone: String)
    fun openRegister()
    fun openVerify()

}