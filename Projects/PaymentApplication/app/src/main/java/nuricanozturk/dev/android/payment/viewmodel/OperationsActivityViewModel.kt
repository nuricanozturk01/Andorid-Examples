package nuricanozturk.dev.android.payment.viewmodel

import nuricanozturk.dev.android.payment.OperationsActivity
import java.lang.ref.WeakReference

class OperationsActivityViewModel(activity: OperationsActivity) {
    private val mActivity = WeakReference(activity)

    fun handlePaymentButton() = mActivity.get()?.paymentButtonClick()
    fun handleLoginInfoButton() = mActivity.get()?.loginInformationButtonClick()
    fun handleCloseButton() = mActivity.get()?.closeButtonClick()
}