package nuricanozturk.dev.adroid.app.geonames.repositorylib.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import nuricanozturk.dev.adroid.app.geonames.repositorylib.entity.QueryInfo

@Dao
interface IQueryInfoDao
{
    @Insert
    fun save(queryInfo : QueryInfo)

    @Query("SELECT EXISTS(SELECT * FROM query_info WHERE `query` = :query)")
    fun isExistByQuery(query : String) : Boolean

    @Query("SELECT COUNT(*) FROM query_info")
    fun getCount() : Int

    @Query("DELETE FROM query_info WHERE `query` = :query")
    fun removeQueryInfoByQuery(query : String)
}