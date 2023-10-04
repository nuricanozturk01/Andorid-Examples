package nuricanozturk.dev.android.multipleactivity.viewmodel

import nuricanozturk.dev.android.multipleactivity.RegisterActivity
import java.lang.ref.WeakReference


class RegisterActivityViewModel(activity : RegisterActivity,
                                var username : String = "",
                                var password : String = "")
{

    private val mWeakReference = WeakReference(activity)
    fun handleRegisterButton() = mWeakReference.get()?.registerButtonClicked()
    fun handleChangeEducation(pos: Int) = mWeakReference.get()?.handleChangeEducation(pos)
}