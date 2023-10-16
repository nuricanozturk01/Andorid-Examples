package nuricanozturk.dev.android.app.data.service.dto

import java.io.Serializable

data class PaymentSaveDTO(var unitPrice : Double = 0.0,
                          var username : String = "",
                          var quantity : Double = 0.0,
                          var description : String = "") : Serializable
{
    override fun toString() : String = "Product Name: $description - $quantity * $unitPrice = ${String.format("%.2f",quantity * unitPrice)}"
}