package nuricanozturk.dev.android.multipleactivity.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.Month
import java.time.temporal.ChronoUnit

data class RegisterInfo @RequiresApi(Build.VERSION_CODES.O) constructor(var firstName : String = "",
                                                                        var middleName : String? = "",
                                                                        var lastName : String = "",
                                                                        var email : String = "",
                                                                        var password : String = "",
                                                                        var birthDate : LocalDate = LocalDate.now())
{
    val age : Double
        @RequiresApi(Build.VERSION_CODES.O) get() = ChronoUnit.DAYS.between(birthDate, LocalDate.now()) / 365.0

    val fullName : String
        get()
        {
            var middleStr = middleName ?: "" //Elvis operatör kullanmak için bu şekilde yapıldı

            middleStr += if (middleStr.isNotEmpty()) " " else ""

            return "${"$firstName "}$middleStr$lastName"
        }
}