package nuricanozturk.dev.android.repositorylib.entity

import java.time.LocalDate

data class User(var username : String,
                var password : String,
                var firstName : String,
                var middleName : String?,
                var birthDate : LocalDate,
                var registerDate : LocalDate,
                var lastName : String,)
{}