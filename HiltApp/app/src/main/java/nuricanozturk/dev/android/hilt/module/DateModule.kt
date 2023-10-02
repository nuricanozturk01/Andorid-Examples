package nuricanozturk.dev.android.hilt.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import java.time.LocalDate

@Module
@InstallIn(ActivityComponent::class) // scope boyu
object DateModule
{

    @Provides
    fun createLocalDate() : LocalDate = LocalDate.now()

}