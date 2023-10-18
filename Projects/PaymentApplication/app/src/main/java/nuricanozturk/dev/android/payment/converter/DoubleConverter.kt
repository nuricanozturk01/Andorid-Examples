package nuricanozturk.dev.android.payment.converter

import androidx.databinding.InverseMethod
import java.lang.NumberFormatException

object DoubleConverter
{
    @InverseMethod("toStr")
    fun toDoubleNumber(string : String) : Double
    {
        var result = 0.0
        try
        {
            result = string.toDouble();
        }
        catch (ignored : NumberFormatException) {

        }
        return result;
    }

    fun toStr(number : Double) : String = number.toString()
}