package nuricanozturk.dev.android.multipleactivity.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import nuricanozturk.dev.android.multipleactivity.RegisterActivity
import java.lang.ref.WeakReference


class RegisterActivityViewModel(activity : RegisterActivity)
{
    private val mWeakReference = WeakReference(activity)

    fun handleRegisterButton() = mWeakReference.get()?.registerButtonClicked()

    fun handleChangeEducation(pos : Int) = mWeakReference.get()?.handleChangeEducation(pos)
    fun handleChangeDay(pos : Int) = mWeakReference.get()?.handleChangeDay(pos)
    @RequiresApi(Build.VERSION_CODES.O)
    fun handleChangeMonth(pos : Int) = mWeakReference.get()?.birthDateMonthSpinnerItemSelected(pos)
    fun handleChangeYear(pos : Int) = mWeakReference.get()?.handleChangeYear(pos)
}