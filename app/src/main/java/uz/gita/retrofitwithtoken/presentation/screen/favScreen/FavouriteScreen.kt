package uz.gita.retrofitwithtoken.presentation.screen.favScreen

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.retrofitwithtoken.R
import uz.gita.retrofitwithtoken.databinding.ScreenFavBinding
import uz.gita.retrofitwithtoken.presentation.adapter.HomeAdapter
import uz.gita.retrofitwithtoken.presentation.screen.homeScreen.HomeViewModel
import uz.gita.retrofitwithtoken.presentation.viewModel.HomeViewModelImpl

/*
 * Arzigul Nazarbaeva
 * 12/20/2022, Tuesday, 4:43 PM
*/


@AndroidEntryPoint
class FavouriteScreen : Fragment(R.layout.screen_fav) {
    private val binding: ScreenFavBinding by viewBinding(ScreenFavBinding::bind)
    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()
    private val adapter by lazy { HomeAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.recyclerView.adapter = adapter

        viewModel.getFavBooks()

        viewModel.favBookList.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }

}