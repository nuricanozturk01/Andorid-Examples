package nuricanozturk.dev.android.hilt.module.formatter

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nuricanozturk.dev.android.hilt.annotation.DateFormatterIntercepter
import java.time.format.DateTimeFormatter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DateFormatter
{

    @Provides
    @Singleton
    @DateFormatterIntercepter
    fun provideDateTimeFormatter() : DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

}