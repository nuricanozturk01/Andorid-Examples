package nuricanozturk.dev.adroid.app.geonames.wikisearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import nuricanozturk.dev.adroid.app.geonames.repositorylib.dal.WikiSearchServiceHelper
import nuricanozturk.dev.adroid.app.geonames.repositorylib.entity.QueryInfo
import nuricanozturk.dev.adroid.app.geonames.wikisearch.api.IGeonamesWikiSearchService
import nuricanozturk.dev.adroid.app.geonames.wikisearch.api.WikiInfo
import nuricanozturk.dev.adroid.app.geonames.wikisearch.api.WikiSearch
import nuricanozturk.dev.adroid.app.geonames.wikisearch.databinding.ActivityWikiInfoSearchBinding
import nuricanozturk.dev.adroid.app.geonames.wikisearch.global.QUERY
import nuricanozturk.dev.adroid.app.geonames.wikisearch.global.WIKI_INFO
import nuricanozturk.dev.adroid.app.geonames.repositorylib.entity.WikiInfo as WikiInfoEntity
import nuricanozturk.dev.adroid.app.geonames.wikisearch.viewmodel.WikiInfoSearchActivityViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.BufferedWriter
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStreamWriter
import java.time.LocalDateTime.now
import java.time.format.DateTimeFormatter.ofPattern
import java.util.concurrent.ScheduledExecutorService
import javax.inject.Inject

const val SHARED_PREFERENCES_NAME = "geonames-input"
const val SHARED_PREFERENCES_QUERY = "query"
const val SHARED_PREFERENCES_MAX_ROWS = "maxRows"

/*
Activity'e özgü preference da var. buna sadece sahip olduğu aktivin içinden erişilebilir. Bu durumda bu preference'ı
Activity'e özgü bir şekilde kullanmak istiyorsak, Activity'nin yaşam döngüsüne göre bu preference'ı kullanmamız gerekmektedir.
 */
@AndroidEntryPoint
class WikiInfoSearchActivity : AppCompatActivity()
{
    private lateinit var mBinding : ActivityWikiInfoSearchBinding

    private val mWikiInfo = ArrayList<WikiInfo>()

    @Inject
    lateinit var wikiSearchService : IGeonamesWikiSearchService

    @Inject
    lateinit var mExecutorService : ScheduledExecutorService

    @Inject
    lateinit var mDb : WikiSearchServiceHelper

    override fun onCreate(savedInstanceState : Bundle?)
    {
        val privPref =
            getPreferences(MODE_PRIVATE).getString("LAST_OPEN_DATE_TIME", now().format(ofPattern("dd/MM/yyyy kk:mm:ss")))
        Toast.makeText(this, "Last open time: $privPref", Toast.LENGTH_SHORT).show()
        super.onCreate(savedInstanceState)
        initialize()
    }

    private fun saveData(data : String?, bw : BufferedWriter)
    {
        try
        {
            bw.write("$data\r\n")
            bw.flush()
        }
        catch (ex : IOException)
        {
            runOnUiThread {
                Toast.makeText(this, "IO Error while saving:${ex.message}", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun saveCache(bw : BufferedWriter)
    {
        val count = mWikiInfo.size

        for (i in 1..<count) saveData(mWikiInfo[i].summary, bw)
    }

    private fun saveCacheCallback()
    {
        try
        {
            BufferedWriter(OutputStreamWriter(FileOutputStream(File(cacheDir, "data.dat")))).use(this::saveCache)
        }
        catch (ex : IOException)
        {
            runOnUiThread {
                Toast.makeText(this, "IO Error while opening:${ex.message}", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun initData()
    {
        mBinding.viewModel = WikiInfoSearchActivityViewModel(this)
        mBinding.wikiInfoAdapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, mWikiInfo)
        loadData()

    }

    private fun loadData()
    {
        val pref = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE)
        mBinding.query = pref.getString(SHARED_PREFERENCES_QUERY, "ankara")
        mBinding.maxRows = pref.getInt(SHARED_PREFERENCES_MAX_ROWS, 10)
    }


    override fun onDestroy()
    {
        val privEditor = getPreferences(MODE_PRIVATE).edit()
        val editor =
            getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE).edit() // shared preferences is a key-value pair storage
        privEditor.putString("LAST_OPEN_DATE_TIME", now().format(ofPattern("dd/MM/yyyy kk:mm:ss")))
        editor.putString(SHARED_PREFERENCES_QUERY, mBinding.query)
        editor.putInt(SHARED_PREFERENCES_MAX_ROWS, mBinding.maxRows!!) // commit() is synchronous, apply() is asynchronous
        privEditor.apply()
        editor.apply() // editor.commit()
        super.onDestroy()
    }

    private fun initialize()
    {
        initBinding()
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_wiki_info_search)
        initData()
    }

    private fun isExistByQuery(query : String,
                               successCallback : () -> Unit,
                               failCallback : () -> Unit)
    {
        mExecutorService.execute {
            val exists = mDb.isExistByQuery(query)
            runOnUiThread {
                if (exists) successCallback() else failCallback()
            }
        }
    }


    private fun handleGetButtonClickedCallback()
    {
        mBinding.wikiInfoAdapter!!.clear()

        val call =
            wikiSearchService.findByQuery(mBinding.query!!, mBinding.maxRows!!, "nuricanozturk")

        // enqueue metodu işini asenkron olarak yapar, fakat onResponse ve onFailure metotları main thread'de çalışır
        call.enqueue(object : Callback<WikiSearch>
        {
            override fun onResponse(call : Call<WikiSearch>, response : Response<WikiSearch>)
            {
                val wikiSearch = response.body()

                if (wikiSearch?.wikiInfo != null)
                {
                    wikiSearch.wikiInfo.forEach() { mBinding.wikiInfoAdapter!!.add(it) }
                    mBinding.wikiInfoAdapter!!.notifyDataSetChanged()
                    mExecutorService.execute { saveCacheCallback() }

                } else Toast.makeText(this@WikiInfoSearchActivity, "No data found", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onFailure(call : Call<WikiSearch>, t : Throwable)
            {
                Toast.makeText(this@WikiInfoSearchActivity, "Exception occured", Toast.LENGTH_SHORT)
                    .show()
            }

        })
    }

    fun handleGetButtonClicked()
    {
        isExistByQuery(mBinding.query!!, { findExistingWikiInfo() }, { handleGetButtonClickedCallback() })
    }

    private fun findExistingWikiInfo()
    {
        mBinding.wikiInfoAdapter!!.clear()
        mExecutorService.execute {
            val wikiInfo = mDb.getWikiInfoByQuery(mBinding.query!!)!!

            runOnUiThread {
                mBinding.wikiInfoAdapter!!.add(WikiInfo(wikiInfo.summary, wikiInfo.title))
                mBinding.wikiInfoAdapter!!.notifyDataSetChanged()

                Toast.makeText(this@WikiInfoSearchActivity, "Data is exists!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun handleListViewItemClicked(pos : Int)
    {
        val wikiInfo = mBinding.wikiInfoAdapter!!.getItem(pos)

        Intent(this, WikiInfoSummaryActivity::class.java).apply {
            putExtra(WIKI_INFO, wikiInfo)
            putExtra(QUERY, mBinding.query!!)
            startActivity(this)
        }
    }

    fun handleSaveAllClicked()
    {
        mExecutorService.execute { handleAllSaveClickedCallback() }
    }

    private fun handleAllSaveClickedCallback()
    {
        val wikiInfos = mBinding.wikiInfoAdapter!!
        val list = mutableListOf<WikiInfo>()

        for (i in 0 until wikiInfos.count) list.add(wikiInfos.getItem(i)!!)

        mExecutorService.execute {
            list.forEachIndexed { index, wikiInfo ->
                val q = if (index == 0) mBinding.query!! else mBinding.query + "-" + index

                val queryInfo = QueryInfo(query = q, lastQueryTime = now())
                mDb.save(queryInfo)

                val wi =
                    WikiInfoEntity(query = q, summary = wikiInfo.summary!!, title = wikiInfo.title!!, longitude = wikiInfo.longitude, latitude = wikiInfo.latitude, countryCode = wikiInfo.countryCode)
                mDb.save(wi)
            }
        }

        runOnUiThread {
            Toast.makeText(this@WikiInfoSearchActivity, "All data saved", Toast.LENGTH_SHORT).show()
        }
    }

}