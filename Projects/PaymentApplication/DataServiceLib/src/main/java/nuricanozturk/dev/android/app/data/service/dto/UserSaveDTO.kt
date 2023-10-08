package nuricanozturk.dev.android.app.data.service.dto

import java.io.Serializable
import java.time.LocalDate

data class UserSaveDTO(var username : String = "",
                       var password : String = "",
                       var firstName : String = "",
                       var lastName : String = "",
                       var birthDate : LocalDate = LocalDate.of(1990, 5, 25),
                       var middleName : String? = null) : Serializable
{}