package nuricanozturk.dev.android.repositorylib.entity

import java.io.Serializable
import java.time.LocalDateTime

data class LoginInfo(var id : Long,
                     var username : String,
                     var loginDateTime : LocalDateTime,
                     var success : Boolean) : Serializable
{}