package nuricanozturk.dev.android.multipleactivity.binding

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.databinding.InverseMethod
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object RegisterBirthDateStringConverter {
    @RequiresApi(Build.VERSION_CODES.O)
    private val mFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private var mFail = false

    val fail: Boolean
        get() = mFail

    var failStr = ""

    @RequiresApi(Build.VERSION_CODES.O)
    @InverseMethod("toStr")
    @JvmStatic
    fun toLocalDate(str: String) : LocalDate
    {
        var result = LocalDate.of(1995, Month.OCTOBER, 12)

        try {
            mFail = false
            result = LocalDate.parse(str, mFormatter)
        }
        catch (ignore: DateTimeParseException) {
            mFail = true
        }

        return result
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @JvmStatic
    fun toStr(birthDate: LocalDate) = mFormatter.format(birthDate)
}