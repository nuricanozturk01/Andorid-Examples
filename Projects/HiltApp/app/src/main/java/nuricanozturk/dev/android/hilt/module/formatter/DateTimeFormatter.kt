package nuricanozturk.dev.android.hilt.module.formatter

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import nuricanozturk.dev.android.hilt.annotation.DateTimeFormatterIntercepter
import java.time.format.DateTimeFormatter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DateTimeFormatter
{
    @Provides
    @Singleton
    @DateTimeFormatterIntercepter
    fun provideDateTimeFormatter(@ApplicationContext context : Context) : DateTimeFormatter
    {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy kk:mm:ss")
    }
}