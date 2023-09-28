package nuricanozturk.dev.android.multipleactivity.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import nuricanozturk.dev.android.multipleactivity.LoginActivity
import java.lang.ref.WeakReference


class LoginActivityViewModel(activity : LoginActivity)
{
    private val mWeakReference = WeakReference(activity)
    @RequiresApi(Build.VERSION_CODES.O)
    fun handleLoginButton() = mWeakReference.get()?.handleLoginButton()
}