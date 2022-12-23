package uz.gita.retrofitwithtoken.data.source.local.shp

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.gita.retrofitwithtoken.app.App
import uz.gita.retrofitwithtoken.utils.SharedPreference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalStorage @Inject constructor(
    @ApplicationContext val context: Context
) : SharedPreference(context) {

    var token: String by strings()
    var isLogin: Boolean by booleans()

}