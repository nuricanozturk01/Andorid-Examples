package nuricanozturk.dev.android.hilt.datetime

import nuricanozturk.dev.android.hilt.annotation.TimeFormatterIntercepter
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class TimeInfo @Inject constructor()
{
    @Inject
    lateinit var time: LocalTime

    @Inject
    @TimeFormatterIntercepter
    lateinit var formatter : DateTimeFormatter

    override fun toString()  = formatter.format(time)
}