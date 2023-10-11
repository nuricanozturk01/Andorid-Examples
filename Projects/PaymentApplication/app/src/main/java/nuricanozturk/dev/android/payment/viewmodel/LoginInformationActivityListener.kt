package nuricanozturk.dev.android.payment.viewmodel

import nuricanozturk.dev.android.payment.LoginInformationActivity
import java.lang.ref.WeakReference

class LoginInformationActivityListener (activity: LoginInformationActivity)
{
    private val mActivity = WeakReference(activity)

    fun handleSuccessLoginButton() = mActivity.get()?.successLoginButtonClicked()
    fun handleFailLoginButton() = mActivity.get()?.successFailButtonClicked()
    fun handleCloseButton() = mActivity.get()?.closeButtonClicked()
}