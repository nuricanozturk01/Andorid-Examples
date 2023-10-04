package nuricanozturk.dev.android.hilt.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import java.time.LocalTime

@Module
@InstallIn(ActivityComponent::class) // scope boyu
object TimeModule
{

    @Provides
    fun createLocalTime() : LocalTime = LocalTime.now()

}