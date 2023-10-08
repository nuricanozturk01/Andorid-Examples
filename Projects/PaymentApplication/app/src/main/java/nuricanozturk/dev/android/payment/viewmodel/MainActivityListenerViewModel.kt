package nuricanozturk.dev.android.payment.viewmodel

import nuricanozturk.dev.android.payment.MainActivity
import java.lang.ref.WeakReference

class MainActivityListenerViewModel(activity : MainActivity)
{
    private val mWeakActivity = WeakReference(activity)
    fun handleRegisterButton() = mWeakActivity.get()?.registerButtonClicked()
    fun handleLoginButton() = mWeakActivity.get()?.loginButtonClicked()
}