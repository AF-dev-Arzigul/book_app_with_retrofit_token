package uz.gita.retrofitwithtoken.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.mocklets.pluto.Pluto
import com.mocklets.pluto.PlutoInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.retrofitwithtoken.data.source.remote.service.AuthApi
import uz.gita.retrofitwithtoken.data.source.remote.service.BookApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDbModule {

    @[Provides Singleton]
    fun loggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @[Provides Singleton]
    fun client(@ApplicationContext context: Context, loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        Pluto.initialize(context)
        return OkHttpClient.Builder()
            .addInterceptor(PlutoInterceptor())
            .addInterceptor(loggingInterceptor)
//            .addInterceptor(ChuckerInterceptor(context))
            .build()
    }

    @[Provides Singleton]
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://143.198.48.222:82/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun authApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

    @Provides
    fun bookApi(retrofit: Retrofit): BookApi = retrofit.create(BookApi::class.java)
}