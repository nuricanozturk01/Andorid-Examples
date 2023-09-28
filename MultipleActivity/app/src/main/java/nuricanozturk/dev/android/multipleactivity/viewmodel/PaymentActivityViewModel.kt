package nuricanozturk.dev.android.multipleactivity.viewmodel

import nuricanozturk.dev.android.multipleactivity.PaymentActivity


data class PaymentActivityViewModel(
    private val activity: PaymentActivity,
    var unitPriceStr: String = "",
    var name: String = "",
    var quantityStr: String = "",
) {

    fun handlePayButton() = activity.payButtonClicked();

    fun handleExitButton() = activity.exitButtonClicked();

    fun handleClearButton() = activity.clearButtonClicked();
}