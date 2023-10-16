package nuricanozturk.dev.android.repositorylib.entity

import java.io.Serializable

data class Payment(var id : Long = 0L,
                   var username : String = "",
                   var unitPrice : Double = 0.0,
                   var quantity : Double = 0.0,
                   var description : String = "") : Serializable
