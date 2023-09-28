package nuricanozturk.dev.android.multipleactivity.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import nuricanozturk.dev.android.multipleactivity.MainActivity

data class MainActivityViewModel(private val activity: MainActivity) {

    fun handleRegisterButton() = activity.registerButtonClick()
    @RequiresApi(Build.VERSION_CODES.O)
    fun handleLoginButton() = activity.loginButtonClick()
    fun handleLogoutButton() = activity.logoutButtonClick()
}