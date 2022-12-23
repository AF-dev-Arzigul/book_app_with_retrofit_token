package uz.gita.retrofitwithtoken.presentation.screen.loginScreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rejowan.cutetoast.CuteToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.retrofitwithtoken.R
import uz.gita.retrofitwithtoken.databinding.ScreenSigninBinding
import uz.gita.retrofitwithtoken.presentation.viewModel.SignInViewModelImpl
import uz.gita.retrofitwithtoken.utils.connectivityManager

@AndroidEntryPoint
class SignInScreen : Fragment(R.layout.screen_signin) {
    private val binding: ScreenSigninBinding by viewBinding(ScreenSigninBinding::bind)
    private val viewModel: SignInViewModel by viewModels<SignInViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        connectivityManager(
            requireContext(),
            { CuteToast.ct(requireContext(), "Connected", CuteToast.LENGTH_SHORT, CuteToast.SUCCESS, true).show(); },
            { CuteToast.ct(requireContext(), "No Internet", CuteToast.LENGTH_SHORT, CuteToast.ERROR, true).show(); }
        )

        binding.tvCreateAccount.setOnClickListener {
            viewModel.openRegister()
        }

        binding.btnSignIn.setOnClickListener {
//            Log.d("AAAAA", "+998${binding.metPhone.unMaskedText}")
//            Log.d("AAAAA", "${binding.etPassword.text}")
            val phone = binding.metPhone.unMaskedText.toString()
            val password = binding.etPassword.text.toString()
            if (phone.isNotEmpty() && password.isNotEmpty()) {
                Log.d("ZZZZZ", "$password, +998$phone")
                viewModel.signIn(password, "+998$phone")
            }
        }

        viewModel.successFlow.onEach {
            Log.d("ZZZZZ", "working")
            viewModel.openVerify()
        }.launchIn(lifecycleScope)

        viewModel.failureFlow.onEach {
            Toast.makeText(requireContext(), "Failed to load data", Toast.LENGTH_SHORT).show()
        }.launchIn(lifecycleScope)

        viewModel.loading.onEach {

        }.launchIn(lifecycleScope)

    }
}