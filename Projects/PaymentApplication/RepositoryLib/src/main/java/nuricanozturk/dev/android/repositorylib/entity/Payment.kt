package nuricanozturk.dev.android.repositorylib.entity

import java.io.Serializable

data class Payment(var id : Long,
                   var username : String,
                   var unitPrice : Double,
                   var quantity : Int,
                   var description : Int) : Serializable
{}