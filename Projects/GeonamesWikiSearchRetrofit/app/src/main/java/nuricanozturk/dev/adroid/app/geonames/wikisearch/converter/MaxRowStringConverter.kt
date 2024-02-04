package nuricanozturk.dev.adroid.app.geonames.wikisearch.converter

import androidx.databinding.InverseMethod

object MaxRowStringConverter
{
    @InverseMethod("toStr")
    fun toInt(maxRow : String) : Int
    {
        var result = 0
        try
        {
            result = maxRow.toInt()
        }
        catch (_ : NumberFormatException)
        {

        }
        return result
    }

    fun toStr(maxRow : Int) : String = maxRow.toString()
}