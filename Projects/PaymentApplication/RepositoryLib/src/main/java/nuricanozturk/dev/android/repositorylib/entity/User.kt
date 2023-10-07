package nuricanozturk.dev.android.repositorylib.entity

import java.io.Serializable
import java.time.LocalDate

data class User(var username : String,
                var password : String,
                var firstName : String,
                var middleName : String?,
                var lastName : String,
                var birthDate : LocalDate,
                var registerDate : LocalDate) : Serializable
{
    constructor(username: String, password: String, firstName: String, lastName: String,
                birthDate: LocalDate, registerDate: LocalDate)
            :this(username, password, firstName, null, lastName, birthDate, registerDate)
}