package nuricanozturk.dev.android.multipleactivity.viewmodel

import nuricanozturk.dev.android.multipleactivity.PaymentActivity


data class PaymentActivityViewModel(
    private val activity: PaymentActivity,
    var username: String = "",
    var password: String = ""
) {

}