package nuricanozturk.dev.android.app.data.service.dto

import java.io.Serializable
import java.time.LocalDateTime

data class LoginInfoStatusDTO(var username : String = "",
                              var password : String = "",
                              var loginDateTimeStr : String,
                              var success : Boolean = true) : Serializable
{
    override fun toString() = "Login DateTime: $loginDateTimeStr, Status: ${if (success) "Success" else "Fail"}"
}