package uz.gita.retrofitwithtoken.presentation.screen.booksScreen

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
import uz.gita.retrofitwithtoken.databinding.ScreenBooksBinding
import uz.gita.retrofitwithtoken.presentation.adapter.UserAdapter
import uz.gita.retrofitwithtoken.presentation.screen.homeScreen.HomeViewModel
import uz.gita.retrofitwithtoken.presentation.viewModel.HomeViewModelImpl

/*
 * Arzigul Nazarbaeva
 * 12/20/2022, Tuesday, 4:42 PM
*/

@AndroidEntryPoint
class BooksScreen : Fragment(R.layout.screen_books) {
    private val binding: ScreenBooksBinding by viewBinding(ScreenBooksBinding::bind)
    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()
    private val adapter by lazy { UserAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.recyclerView.adapter = adapter

        viewModel.getUsers()

        viewModel.userList.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)

    }

}