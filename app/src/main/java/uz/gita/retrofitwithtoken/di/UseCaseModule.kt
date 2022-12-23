package uz.gita.retrofitwithtoken.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.retrofitwithtoken.domain.usecase.*
import uz.gita.retrofitwithtoken.domain.usecase.impl.*


@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun homeUseCase(useCase: HomeUseCaseImpl): HomeUseCase

    @Binds
    fun loginUseCase(useCase: SignInUseCaseImpl): SignInUseCase

    @Binds
    fun loginVerifyUseCase(useCase: SignInVerifyUseCaseImpl): SignInVerifyUseCase

    @Binds
    fun signUpUseCase(useCase: SignUpUseCaseImpl): SignUpUseCase

    @Binds
    fun signUpVerifyUseCase(useCase: SignUpVerifyUseCaseImpl): SignUpVerifyUseCase

}