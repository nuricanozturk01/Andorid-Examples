package nuricanozturk.dev.android.hilt.module.formatter

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nuricanozturk.dev.android.hilt.annotation.TimeFormatterIntercepter
import java.time.format.DateTimeFormatter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TimeFormatter
{

    @Provides
    @Singleton
    @TimeFormatterIntercepter
    fun provideDateTimeFormatter() : DateTimeFormatter =
        DateTimeFormatter.ofPattern("kk:mm:ss")

}