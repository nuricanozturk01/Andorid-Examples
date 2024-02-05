package nuricanozturk.dev.adroid.app.geonames.repositorylib.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import nuricanozturk.dev.adroid.app.geonames.repositorylib.database.GeonamesApplicationDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GeonamesDatabaseModule
{
    @Provides
    @Singleton
    fun provideGeonamesDatabase(@ApplicationContext context : Context) : GeonamesApplicationDatabase
    {
        return Room.databaseBuilder(context, GeonamesApplicationDatabase::class.java, "geonames_db.sqlite3")
            .build()
    }

}