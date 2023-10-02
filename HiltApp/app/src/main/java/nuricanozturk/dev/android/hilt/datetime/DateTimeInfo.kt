package nuricanozturk.dev.android.hilt.datetime

import nuricanozturk.dev.android.hilt.annotation.DateTimeFormatterIntercepter
import nuricanozturk.dev.android.hilt.module.SystemDateTimeIntercepter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

// COnstructor injection
class DateTimeInfo @Inject constructor(@DateTimeFormatterIntercepter val formatter : DateTimeFormatter,
                                       @SystemDateTimeIntercepter val dateTime : LocalDateTime)
{
    override fun toString() = formatter.format(dateTime)
}