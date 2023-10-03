package nuricanozturk.dev.android.hilt.datetime

import android.content.Context
import android.widget.Toast
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import nuricanozturk.dev.android.hilt.annotation.DateTimeFormatterIntercepter
import nuricanozturk.dev.android.hilt.module.SystemDateTimeIntercepter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

// Constructor injection
class DateTimeInfo @Inject constructor(
    @DateTimeFormatterIntercepter val formatter: DateTimeFormatter,
    @SystemDateTimeIntercepter val dateTime: LocalDateTime,
    @ActivityContext context: Context
)
{
    init {
        Toast.makeText(context, "DateTimeInfo: -> $this",
                       Toast.LENGTH_LONG).show()
    }
    override fun toString() : String = formatter.format(dateTime)
}