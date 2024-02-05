package nuricanozturk.dev.adroid.app.geonames.repositorylib.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import nuricanozturk.dev.adroid.app.geonames.repositorylib.entity.WikiInfo

@Dao
interface IWikiInfoDao
{
    @Insert
    fun save(wikiInfo : WikiInfo)

    @Query("SELECT * FROM wiki_info WHERE `query` = :query order by id desc LIMIT 1")
    fun getWikiInfoByQuery(query : String) : WikiInfo?

    @Query("SELECT * FROM wiki_info")
    fun getAllWikiInfo() : List<WikiInfo>
}