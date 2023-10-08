package nuricanozturk.dev.android.payment.viewmodel

import nuricanozturk.dev.android.payment.MainActivity
import java.lang.ref.WeakReference

class MainActivityListenerViewModel(private var activity : MainActivity)
{
    private val mWeakActivity = WeakReference(activity)
    fun handleRegisterButton() = mWeakActivity.get()?.registerButtonClicked()
}