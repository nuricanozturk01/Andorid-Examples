package nuricanozturk.dev.android.multipleactivity.viewmodel

data class PaymentInfo (var productName : String = "", var unitPrice: Double = 0.0, var quantity: Int = 0){
    val total: Double
        get() = unitPrice * quantity
    override fun toString() = "$productName, $unitPrice * $quantity = ${unitPrice * quantity}"
}