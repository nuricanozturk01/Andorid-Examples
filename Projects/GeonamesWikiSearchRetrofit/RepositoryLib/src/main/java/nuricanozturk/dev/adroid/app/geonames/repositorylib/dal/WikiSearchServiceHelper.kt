package nuricanozturk.dev.adroid.app.geonames.repositorylib.dal

import nuricanozturk.dev.adroid.app.geonames.repositorylib.dao.IQueryInfoDao
import nuricanozturk.dev.adroid.app.geonames.repositorylib.dao.IWikiInfoDao
import nuricanozturk.dev.adroid.app.geonames.repositorylib.entity.QueryInfo
import nuricanozturk.dev.adroid.app.geonames.repositorylib.entity.WikiInfo
import javax.inject.Inject

class WikiSearchServiceHelper @Inject constructor()
{
    @Inject
    lateinit var mQueryInfoDao : IQueryInfoDao

    @Inject
    lateinit var mWikiInfo : IWikiInfoDao

    fun isExistByQuery(query : String) : Boolean
    {
        try
        {
            return mQueryInfoDao.isExistByQuery(query)
        }
        catch (e : Exception)
        {
            throw e
        }
    }

    fun getCount() : Int
    {
        try
        {
            return mQueryInfoDao.getCount()
        }
        catch (e : Exception)
        {
            throw e
        }
    }

    fun save(queryInfo : QueryInfo)
    {
        try
        {
            mQueryInfoDao.save(queryInfo)
        }
        catch (e : Exception)
        {
            throw e
        }
    }


    fun save(wikiInfo : WikiInfo)
    {
        try
        {
            mWikiInfo.save(wikiInfo)
        }
        catch (e : Exception)
        {
            throw e
        }
    }

    fun getWikiInfoByQuery(query : String) : WikiInfo?
    {
        try
        {
            return mWikiInfo.getWikiInfoByQuery(query)
        }
        catch (e : Exception)
        {
            throw e
        }
    }


    fun getAllWikiInfo() : List<WikiInfo>
    {
        try
        {
            return mWikiInfo.getAllWikiInfo()
        }
        catch (e : Exception)
        {
            throw e
        }
    }

    fun removeQueryInfo(mQuery : String)
    {
        try
        {
            mQueryInfoDao.removeQueryInfoByQuery(mQuery)
        }
        catch (e : Exception)
        {
            throw e
        }
    }

    /*fun saveAll(list : List<WikiInfo>)
    {
        try
        {
            mWikiInfo.saveAll(list)
        }
        catch (e : Exception)
        {
            throw e
        }
    }

    fun saveAll(toList : List<QueryInfo>)
    {
        try
        {
            mQueryInfoDao.saveAll(toList)
        }
        catch (e : Exception)
        {
            throw e
        }
    }*/
}
