package nuricanozturk.dev.android.hilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    @Inject
    lateinit var dateTime : LocalDateTime

    @Inject
    lateinit var dateTimeFormatter : DateTimeFormatter

    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this, dateTimeFormatter.format(dateTime), Toast.LENGTH_LONG).show()
    }
}