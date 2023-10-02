package nuricanozturk.dev.android.hilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dagger.hilt.android.AndroidEntryPoint
import nuricanozturk.dev.android.hilt.datetime.DateInfo
import nuricanozturk.dev.android.hilt.datetime.DateTimeInfo
import nuricanozturk.dev.android.hilt.datetime.TimeInfo
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    @Inject
    lateinit var dateTimeInfo: DateTimeInfo // Field Injection

    @Inject
    lateinit var dateInfo: DateInfo

    @Inject
    lateinit var timeInfo: TimeInfo
    private fun showDateTime()
    {
        val sb = StringBuilder()

        sb.append("DateTime:").append(dateTimeInfo.toString()).append("\n")
            .append("Date:").append(dateInfo.toString()).append("\n")
            .append("Time:").append(timeInfo.toString())

        Toast.makeText(this, sb, Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun onResume()
    {
        super.onResume()
        showDateTime()
    }

}