package nuricanozturk.dev.android.hilt.datetime


import nuricanozturk.dev.android.hilt.annotation.DateFormatterIntercepter
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class DateInfo @Inject constructor()
{
    @Inject
    lateinit var date: LocalDate

    @Inject
    @DateFormatterIntercepter
    lateinit var formatter : DateTimeFormatter

    override fun toString()  = formatter.format(date)
}