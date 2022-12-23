package uz.gita.retrofitwithtoken.presentation.screen.signUpScreen

import uz.gita.retrofitwithtoken.data.source.remote.dto.request.AuthRequest.SignUp
import uz.gita.retrofitwithtoken.presentation.viewModel.BaseViewModel

interface SignUpViewModel : BaseViewModel {
    fun openVerify()
    fun signUp(signUpRequest: SignUp, rePassword: String)
}