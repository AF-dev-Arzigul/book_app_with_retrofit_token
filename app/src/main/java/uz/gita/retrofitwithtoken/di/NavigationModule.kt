package uz.gita.retrofitwithtoken.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.retrofitwithtoken.navigation.Handler
import uz.gita.retrofitwithtoken.navigation.NavigationDispatcher
import uz.gita.retrofitwithtoken.navigation.Navigator


@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    fun bindsNavigator(impl: NavigationDispatcher): Navigator

    @Binds
    fun bindsHandler(impl: NavigationDispatcher): Handler

}