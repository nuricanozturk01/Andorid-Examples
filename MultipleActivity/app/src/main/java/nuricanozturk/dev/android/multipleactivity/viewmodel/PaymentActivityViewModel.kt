package nuricanozturk.dev.android.multipleactivity.viewmodel

import nuricanozturk.dev.android.multipleactivity.PaymentActivity
import java.lang.ref.WeakReference


class PaymentActivityViewModel(activity: PaymentActivity) {

    private val mWeakReference = WeakReference(activity)
    fun handlePayButton() = mWeakReference.get()?.payButtonClicked();

    fun handleExitButton() = mWeakReference.get()?.exitButtonClicked();

    fun handleClearButton() = mWeakReference.get()?.clearButtonClicked();
}