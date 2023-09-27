package nuricanozturk.dev.android.multipleactivity.viewmodel

import nuricanozturk.dev.android.multipleactivity.LoginActivity


data class LoginActivityViewModel(
    private val activity: LoginActivity,
    var username: String = "",
    var password: String = ""
) {

    fun handleLoginButton() = activity.handleLoginButton()
}