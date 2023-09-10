package nuricanozturk.dev.android.viewBinding.viewmodel

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class RegisterInfo(var name: String, var email: String, var date: LocalDate) {
    override fun toString(): String {
        val format = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(date)
        return "$name - [$email - $format]"
    }
}

