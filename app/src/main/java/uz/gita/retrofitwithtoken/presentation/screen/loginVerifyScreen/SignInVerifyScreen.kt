package uz.gita.retrofitwithtoken.presentation.screen.loginVerifyScreen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.retrofitwithtoken.R
import uz.gita.retrofitwithtoken.databinding.ScreenSigninVerifyBinding
import uz.gita.retrofitwithtoken.presentation.viewModel.SignInVerifyViewModelImpl

@AndroidEntryPoint
class SignInVerifyScreen : Fragment(R.layout.screen_signin_verify) {
    private val binding: ScreenSigninVerifyBinding by viewBinding(ScreenSigninVerifyBinding::bind)
    private val viewModel: SignInVerifyViewModel by viewModels<SignInVerifyViewModelImpl>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnSignIn.setOnClickListener {
            viewModel.verifyCode(binding.smsConfirmation.enteredCode)
        }

        viewModel.successFlow.onEach {
            Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
        }.launchIn(lifecycleScope)

        viewModel.failureFlow.onEach {
            Toast.makeText(requireContext(), "Fail", Toast.LENGTH_SHORT).show()
        }.launchIn(lifecycleScope)

    }

}