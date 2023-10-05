package nuricanozturk.dev.android.multipleactivity.viewmodel

import nuricanozturk.dev.android.multipleactivity.RegisterActivity
import java.lang.ref.WeakReference


class RegisterActivityViewModel(activity : RegisterActivity)
{
    private val mWeakReference = WeakReference(activity)

    fun handleRegisterButton() = mWeakReference.get()?.registerButtonClicked()

    fun handleChangeEducation(pos : Int) = mWeakReference.get()?.handleChangeEducation(pos)
    fun handleChangeDay(pos : Int) = mWeakReference.get()?.handleChangeDay(pos)
    fun handleChangeMonth(pos : Int) = mWeakReference.get()?.handleChangeMonth(pos)
    fun handleChangeYear(pos : Int) = mWeakReference.get()?.handleChangeYear(pos)
}