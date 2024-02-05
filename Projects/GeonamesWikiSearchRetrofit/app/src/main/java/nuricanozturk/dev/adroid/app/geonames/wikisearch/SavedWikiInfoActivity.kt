package nuricanozturk.dev.adroid.app.geonames.wikisearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import nuricanozturk.dev.adroid.app.geonames.repositorylib.dal.WikiSearchServiceHelper
import nuricanozturk.dev.adroid.app.geonames.wikisearch.api.WikiInfo
import nuricanozturk.dev.adroid.app.geonames.wikisearch.databinding.ActivitySavedWikiInfoBinding
import nuricanozturk.dev.adroid.app.geonames.wikisearch.global.QUERY
import nuricanozturk.dev.adroid.app.geonames.wikisearch.global.WIKI_INFO
import nuricanozturk.dev.adroid.app.geonames.wikisearch.viewmodel.SavedWikiInfoActivityViewModel
import java.util.concurrent.ScheduledExecutorService
import javax.inject.Inject

@AndroidEntryPoint
class SavedWikiInfoActivity : AppCompatActivity()
{
    private lateinit var mBinding : ActivitySavedWikiInfoBinding

    @Inject
    lateinit var mServiceHelper : WikiSearchServiceHelper

    @Inject
    lateinit var mThreadPool : ScheduledExecutorService


    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    override fun onResume()
    {
        super.onResume()
        initListView()
    }
    private fun initialize()
    {
        initBinding()
        initData()
        initListView()
    }

    private fun initListView()
    {
        mBinding.wikiInfoAdapter!!.clear()
        mThreadPool.execute {
            mBinding.wikiInfoAdapter!!.addAll(mServiceHelper.getAllWikiInfo())
            mBinding.wikiInfoAdapter!!.notifyDataSetChanged()
        }
    }

    private fun initData()
    {
        mBinding.viewModel = SavedWikiInfoActivityViewModel(this)
        mBinding.wikiInfoAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ArrayList())
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_saved_wiki_info)
    }

    fun handleListViewItemClicked(pos : Int)
    {
        val wikiInfo = mBinding.wikiInfoAdapter!!.getItem(pos)

        Intent(this, WikiInfoSummaryActivity::class.java).apply {
            putExtra(WIKI_INFO, WikiInfo(wikiInfo!!.summary, wikiInfo.title))
            putExtra(QUERY, wikiInfo.query)
            startActivity(this)
        }
    }
}