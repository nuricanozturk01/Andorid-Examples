package nuricanozturk.dev.android.multipleactivity.viewmodel

import java.io.Serializable

data class PaymentInfo(var productName : String = "",
                       var unitPrice : Double = 0.0,
                       var quantity : Int = 0) : Serializable
{
    val total : Double
        get() = unitPrice * quantity

    fun reset()
    {
        productName = ""
        unitPrice = 0.0
        quantity = 0
    }

    override fun toString() = "$productName, $unitPrice * $quantity = ${unitPrice * quantity}"
}