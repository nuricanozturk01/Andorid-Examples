package nuricanozturk.dev.android.multipleactivity.viewmodel

import nuricanozturk.dev.android.multipleactivity.RegisterActivity


data class RegisterActivityViewModel(
    private val activity: RegisterActivity,
    var username: String = "",
    var password: String = ""
) {

    fun handleRegisterButton() = activity.handleRegisterButton()
}