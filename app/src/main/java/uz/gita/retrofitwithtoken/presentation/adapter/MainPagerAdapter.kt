package uz.gita.retrofitwithtoken.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.retrofitwithtoken.presentation.screen.booksScreen.BooksScreen
import uz.gita.retrofitwithtoken.presentation.screen.favScreen.FavouriteScreen
import uz.gita.retrofitwithtoken.presentation.screen.homeScreen.HomeScreen

class MainPagerAdapter(
    fm : FragmentManager,
    lifecycle: Lifecycle,
    private val homeScreen: HomeScreen,
    private val booksScreen: BooksScreen,
    private val favouriteScreen: FavouriteScreen
) : FragmentStateAdapter(fm,lifecycle) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> homeScreen
            1 -> booksScreen
            else -> favouriteScreen
        }
    }
}