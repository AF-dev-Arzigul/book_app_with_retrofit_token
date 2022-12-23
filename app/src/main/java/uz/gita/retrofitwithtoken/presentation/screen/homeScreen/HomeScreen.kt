package uz.gita.retrofitwithtoken.presentation.screen.homeScreen

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
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
import uz.gita.retrofitwithtoken.data.source.local.shp.LocalStorage
import uz.gita.retrofitwithtoken.data.source.remote.dto.request.BookRequest
import uz.gita.retrofitwithtoken.data.source.remote.service.BookApi
import uz.gita.retrofitwithtoken.databinding.ScreenHomeBinding
import uz.gita.retrofitwithtoken.presentation.adapter.HomeAdapter
import uz.gita.retrofitwithtoken.presentation.viewModel.HomeViewModelImpl

@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.screen_home) {
    private val binding: ScreenHomeBinding by viewBinding(ScreenHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()
    private val adapter by lazy { HomeAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.recyclerView.adapter = adapter

        viewModel.getBooks()

        viewModel.bookList.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.failureFlow.onEach {
        }.launchIn(lifecycleScope)

        adapter.setOnItemDeleteClickOnListener {

        }

        adapter.setOnItemEditClickOnListener {

        }

        adapter.setOnItemFavClickOnListener {
            Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()
            viewModel.changeFav(BookRequest.ChangeFavOfBook(it.id))
        }

        binding.btnAdd.setOnClickListener {
            val dialog = Dialog(requireContext())
            dialog.create()

        }

    }

}