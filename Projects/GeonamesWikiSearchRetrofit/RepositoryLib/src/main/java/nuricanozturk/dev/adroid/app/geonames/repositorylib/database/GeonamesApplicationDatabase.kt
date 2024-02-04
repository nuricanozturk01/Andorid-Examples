package nuricanozturk.dev.adroid.app.geonames.repositorylib.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import nuricanozturk.dev.adroid.app.geonames.repositorylib.converter.LocalDateConverter
import nuricanozturk.dev.adroid.app.geonames.repositorylib.converter.LocalDateTimeConverter
import nuricanozturk.dev.adroid.app.geonames.repositorylib.dao.IQueryInfoDao
import nuricanozturk.dev.adroid.app.geonames.repositorylib.dao.IWikiInfoDao
import nuricanozturk.dev.adroid.app.geonames.repositorylib.entity.QueryInfo
import nuricanozturk.dev.adroid.app.geonames.repositorylib.entity.WikiInfo

@Database(entities = [QueryInfo::class, WikiInfo::class], version = 1, exportSchema = false)
@TypeConverters(LocalDateTimeConverter::class, LocalDateConverter::class)
abstract class GeonamesApplicationDatabase : RoomDatabase()
{
    abstract fun createQueryInfoDao() : IQueryInfoDao
    abstract fun createWikiInfoDao() : IWikiInfoDao
}