package nuricanozturk.dev.android.hilt.datetime

import nuricanozturk.dev.android.hilt.annotation.DateTimeFormatterIntercepter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject


class DateTimeInfo @Inject constructor()
{
    @Inject
    lateinit var dateTime: LocalDateTime

    @Inject
    @DateTimeFormatterIntercepter
    lateinit var formatter : DateTimeFormatter

    override fun toString() = formatter.format(dateTime)
}