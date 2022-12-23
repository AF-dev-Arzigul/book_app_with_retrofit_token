package uz.gita.retrofitwithtoken.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.retrofitwithtoken.presentation.screen.booksScreen.BooksScreen
import uz.gita.retrofitwithtoken.presentation.screen.favScreen.FavouriteScreen
import uz.gita.retrofitwithtoken.presentation.screen.homeScreen.HomeScreen

class MainPagerAdapter(f: FragmentActivity) : FragmentStateAdapter(f) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeScreen()
            1 -> BooksScreen()
            else -> FavouriteScreen()
        }
    }
}