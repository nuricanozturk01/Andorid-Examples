package nuricanozturk.dev.adroid.app.geonames.wikisearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import nuricanozturk.dev.adroid.app.geonames.repositorylib.dal.WikiSearchServiceHelper
import nuricanozturk.dev.adroid.app.geonames.wikisearch.api.IGeonamesWikiSearchService
import nuricanozturk.dev.adroid.app.geonames.wikisearch.api.WikiInfo
import nuricanozturk.dev.adroid.app.geonames.wikisearch.api.WikiSearch
import nuricanozturk.dev.adroid.app.geonames.wikisearch.databinding.ActivityMainBinding
import nuricanozturk.dev.adroid.app.geonames.wikisearch.databinding.ActivityWikiInfoSearchBinding
import nuricanozturk.dev.adroid.app.geonames.wikisearch.global.QUERY
import nuricanozturk.dev.adroid.app.geonames.wikisearch.global.WIKI_INFO
import nuricanozturk.dev.adroid.app.geonames.wikisearch.viewmodel.MainActivityViewModel
import nuricanozturk.dev.adroid.app.geonames.wikisearch.viewmodel.WikiInfoSearchActivityViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.ScheduledExecutorService
import javax.inject.Inject
@AndroidEntryPoint
class WikiInfoSearchActivity : AppCompatActivity()
{

    private lateinit var mBinding : ActivityWikiInfoSearchBinding

    @Inject
    lateinit var wikiSearchService : IGeonamesWikiSearchService

    @Inject
    lateinit var mExecutorService : ScheduledExecutorService

    @Inject
    lateinit var mDb : WikiSearchServiceHelper

    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    private fun initData()
    {
        mBinding.viewModel = WikiInfoSearchActivityViewModel(this)
                            mBinding.wikiInfoAdapter =
                                ArrayAdapter(this, android.R.layout.simple_list_item_1, ArrayList())
                            mBinding.query = "izmir"
                            mBinding.maxRows = 3
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

                } else Toast.makeText(this@WikiInfoSearchActivity, "No data found", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call : Call<WikiSearch>, t : Throwable)
            {
                Toast.makeText(this@WikiInfoSearchActivity, "Exception occured", Toast.LENGTH_SHORT).show()
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
}