package nuricanozturk.dev.android.payment.viewmodel

import nuricanozturk.dev.android.payment.PaymentActivity
import java.lang.ref.WeakReference

class PaymentActivityListenerViewModel(activity : PaymentActivity)
{
    private val mActivity = WeakReference(activity)

    fun handleMakePaymentButton() = mActivity.get()?.makePaymentButtonClicked()
    fun handleCloseButton() = mActivity.get()?.closeButtonClicked()
    fun handlePaymentsButton() = mActivity.get()?.paymentsButtonClicked()
}