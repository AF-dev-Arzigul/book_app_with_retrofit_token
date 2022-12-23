package uz.gita.retrofitwithtoken.app

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
//        appContext = this
    }

//    companion object {
//        lateinit var appContext: Context
//
//        // boshqa joylardan turib appContext ga set qilolmaslikni ta'minlaydi
//        private set
//    }

}