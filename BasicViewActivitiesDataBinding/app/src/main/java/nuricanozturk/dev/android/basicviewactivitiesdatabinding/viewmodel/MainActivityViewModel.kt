package nuricanozturk.dev.android.basicviewactivitiesdatabinding.viewmodel

import nuricanozturk.dev.android.basicviewactivitiesdatabinding.MainActivity

class MainActivityViewModel(var activity: MainActivity) {

    fun handleOpenToggleButton(checked: Boolean) {
        activity.openToggleButtonCallback(checked)
    }

    fun handleShowPasswordButton() = activity.handleShowPasswordButton()

    fun handleRegisterButton() = activity.handleRegisterButton()

    fun handleClearButton() = activity.handleClearButton()

    fun handleCloseButton() = activity.handleCloseButton()
}