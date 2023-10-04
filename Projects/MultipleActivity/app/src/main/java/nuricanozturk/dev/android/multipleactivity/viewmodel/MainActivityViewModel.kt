package nuricanozturk.dev.android.multipleactivity.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import nuricanozturk.dev.android.multipleactivity.MainActivity
import java.lang.ref.WeakReference

class MainActivityViewModel(activity : MainActivity)
{
    private val mWeakReference = WeakReference(activity)

    fun handleRegisterButton() = mWeakReference.get()?.registerButtonClick()

    @RequiresApi(Build.VERSION_CODES.O)
    fun handleLoginButton() = mWeakReference.get()?.loginButtonClick()

    fun handleLogoutButton() = mWeakReference.get()?.logoutButtonClick()
}