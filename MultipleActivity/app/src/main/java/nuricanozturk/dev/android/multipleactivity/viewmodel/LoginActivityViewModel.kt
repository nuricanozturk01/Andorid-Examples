package nuricanozturk.dev.android.multipleactivity.viewmodel

import nuricanozturk.dev.android.multipleactivity.LoginActivity


data class LoginActivityViewModel(
    private val activity: LoginActivity
) {

    fun handleLoginButton() = activity.handleLoginButton()
}