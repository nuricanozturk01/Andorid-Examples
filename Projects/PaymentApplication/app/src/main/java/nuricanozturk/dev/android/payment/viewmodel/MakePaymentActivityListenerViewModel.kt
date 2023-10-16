package nuricanozturk.dev.android.payment.viewmodel

import nuricanozturk.dev.android.payment.MakePaymentActivity
import java.lang.ref.WeakReference

class MakePaymentActivityListenerViewModel(activity : MakePaymentActivity)
{
    private val mActivity = WeakReference(activity)

    fun handleMakePaymentButton() = mActivity.get()?.makePaymentButtonClicked()
    fun handleClearButton() = mActivity.get()?.clearButtonClicked()
    fun handleCloseButton() = mActivity.get()?.closeButtonClicked()
}