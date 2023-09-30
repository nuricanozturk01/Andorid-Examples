package nuricanozturk.dev.android.hilt.module

import android.content.Context
import android.widget.Toast
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.time.format.DateTimeFormatter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DateTimeFormatterModule
{
    @Provides
    @Singleton
    fun createDateTimeFormatter(@ApplicationContext context : Context) : DateTimeFormatter
    {
        Toast.makeText(context, "date-time-provider-singletor", Toast.LENGTH_LONG).show()
        return DateTimeFormatter.ofPattern("dd/MM/yyyy kk:mm:ss")
    }
}