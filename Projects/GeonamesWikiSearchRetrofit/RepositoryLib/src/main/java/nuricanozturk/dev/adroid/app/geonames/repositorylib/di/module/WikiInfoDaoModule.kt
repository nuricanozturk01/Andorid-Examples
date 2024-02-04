package nuricanozturk.dev.adroid.app.geonames.repositorylib.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nuricanozturk.dev.adroid.app.geonames.repositorylib.dao.IQueryInfoDao
import nuricanozturk.dev.adroid.app.geonames.repositorylib.dao.IWikiInfoDao
import nuricanozturk.dev.adroid.app.geonames.repositorylib.database.GeonamesApplicationDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WikiInfoDaoModule
{
    @Provides
    @Singleton
    fun provideWikiInfoDao(geonamesDatabase : GeonamesApplicationDatabase) : IWikiInfoDao
    {
        return geonamesDatabase.createWikiInfoDao()
    }
}