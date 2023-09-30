package nuricanozturk.dev.android.hilt.module

import android.content.Context
import android.widget.Toast
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalDateTime

@Module
@InstallIn(ActivityComponent::class) // scope boyu
object DateTimeModule
{

    @Provides
    fun createLocalDateTime(@ApplicationContext context : Context) : LocalDateTime
    {
        Toast.makeText(context, "date-time", Toast.LENGTH_LONG).show()
        return LocalDateTime.now();
    }

}