package nuricanozturk.dev.android.multipleactivity.keys

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val USERNAME = "username"
const val DATE_TIME = "date_time"

const val USER_INFO = "user_info"

@RequiresApi(Build.VERSION_CODES.O)
var DATE = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")).toString()