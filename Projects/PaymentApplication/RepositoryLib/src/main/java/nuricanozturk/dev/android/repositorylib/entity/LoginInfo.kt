package nuricanozturk.dev.android.repositorylib.entity

import java.io.Serializable
import java.time.LocalDateTime

data class LoginInfo(var id : Long = 0,
                     var username : String = "",
                     var password : String = "",
                     var loginDateTime : LocalDateTime = LocalDateTime.now(),
                     var success : Boolean = true) : Serializable
{}