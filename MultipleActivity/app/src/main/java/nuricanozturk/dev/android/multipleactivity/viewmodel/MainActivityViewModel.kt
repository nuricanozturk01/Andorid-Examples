package nuricanozturk.dev.android.multipleactivity.viewmodel

import nuricanozturk.dev.android.multipleactivity.MainActivity

data class MainActivityViewModel(private val activity: MainActivity) {

    fun handleRegisterButton() = activity.registerButtonClick()
    fun handleLoginButton() = activity.loginButtonClick()
    fun handleLogoutButton() = activity.logoutButtonClick()
}