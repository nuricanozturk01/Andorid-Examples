package nuricanozturk.dev.android.hilt.viewmodel

import nuricanozturk.dev.android.hilt.MainActivity
import java.lang.ref.WeakReference

class MainActivityViewModel(activity: MainActivity) {

    private val mWeakReference = WeakReference(activity)

    fun handleCalculateButton() = mWeakReference.get()?.handleCalculateButton()
}