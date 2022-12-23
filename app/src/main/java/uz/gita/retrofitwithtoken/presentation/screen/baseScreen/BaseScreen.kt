package uz.gita.retrofitwithtoken.presentation.screen.baseScreen

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.retrofitwithtoken.R
import uz.gita.retrofitwithtoken.databinding.ScreenBaseBinding
import uz.gita.retrofitwithtoken.presentation.adapter.MainPagerAdapter
import uz.gita.retrofitwithtoken.presentation.screen.homeScreen.HomeViewModel
import uz.gita.retrofitwithtoken.presentation.viewModel.HomeViewModelImpl

/*
 * Arzigul Nazarbaeva
 * 12/20/2022, Tuesday, 4:35 PM
*/


@AndroidEntryPoint
class BaseScreen : Fragment(R.layout.screen_base) {
    private val binding: ScreenBaseBinding by viewBinding(ScreenBaseBinding::bind)
    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.pager.adapter = MainPagerAdapter(requireActivity())
        binding.pager.isUserInputEnabled = false

        binding.bottomNavBarMain.setOnItemSelectedListener {
            val position = when (it.itemId) {
                R.id.book -> {
                    viewModel.getFavBooks()
                    0
                }
                R.id.users_book -> 1
                else -> {
                    Log.d("xxxxx", "keldi")
                    viewModel.getFavBooks()
                    2
                }
            }
            binding.pager.setCurrentItem(position, true)
            true
        }
    }

}