package nuricanozturk.dev.android.multipleactivity.viewmodel

data class PaymentInfo (val productName : String, val unitPrice: Double, val quantity: Int){

    override fun toString() = "$productName, $unitPrice * $quantity = ${unitPrice * quantity}"
}