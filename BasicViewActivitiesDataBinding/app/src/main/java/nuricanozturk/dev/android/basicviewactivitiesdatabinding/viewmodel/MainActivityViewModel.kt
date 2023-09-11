package nuricanozturk.dev.android.basicviewactivitiesdatabinding.viewmodel

import nuricanozturk.dev.android.basicviewactivitiesdatabinding.MainActivity
import java.lang.ref.WeakReference

class MainActivityViewModel(var activity: MainActivity) {

    private val mWeakReference = WeakReference(activity)
    fun handleOpenToggleButton(checked: Boolean) = mWeakReference.get()?.openToggleButtonCheckedChanged(checked)

    fun handleAllowShowPasswordSwitch(checked: Boolean) = mWeakReference.get()?.allowShowPasswordSwitchCheckedChanged(checked)

    fun handleShowPasswordButton() = mWeakReference.get()?.showPasswordButtonClicked()

    fun handleRegisterButton() = mWeakReference.get()?.registerButtonClicked()

    fun handleClearButton() = mWeakReference.get()?.clearButtonClicked()

    fun handleCloseButton() = mWeakReference.get()?.closeButtonClicked()
}