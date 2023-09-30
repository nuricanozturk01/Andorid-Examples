package nuricanozturk.dev.android.hilt.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import java.time.format.DateTimeFormatter

@Module
@InstallIn(ActivityComponent::class)
object DateTimeFormatterModule
{
    @Provides
    fun createDateTimeFormatter() : DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy kk:mm:ss")
}