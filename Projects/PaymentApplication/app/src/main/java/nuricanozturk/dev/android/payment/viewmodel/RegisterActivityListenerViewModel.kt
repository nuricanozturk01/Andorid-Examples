package nuricanozturk.dev.android.payment.viewmodel

import nuricanozturk.dev.android.payment.RegisterActivity
import java.lang.ref.WeakReference

class RegisterActivityListenerViewModel(activity : RegisterActivity)
{
    private val mWeakActivity = WeakReference(activity)
    fun handleRegisterButton() = mWeakActivity.get()?.registerButtonClicked()
    fun handleCloseButton() = mWeakActivity.get()?.closeButtonClicked()
    fun handleClearButton() = mWeakActivity.get()?.clearButtonClicked()
}