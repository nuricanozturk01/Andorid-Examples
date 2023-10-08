package nuricanozturk.dev.android.app.data.service.dto

import java.io.Serializable
import java.time.LocalDateTime

data class LoginInfoDTO(var username : String = "", var password : String = "") : Serializable
{}