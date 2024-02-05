package nuricanozturk.dev.adroid.app.geonames.wikisearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import nuricanozturk.dev.adroid.app.geonames.repositorylib.entity.WikiInfo as WikiInfoEntity
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


    override fun onStart()
    {
        super.onStart()
        initListView()
    }

    private fun initialize()
    {
        initBinding()
        initData()
        initListView()
    }

    private fun initListView() = mThreadPool.execute { initListViewCallback() }

    private fun updateUI(wikiInfoList : List<WikiInfoEntity>)
    {
        val lw = findViewById<ListView>(R.id.list_view_saved_wiki_info)
        val emptyText = findViewById<TextView>(R.id.text_view_empty)
        mBinding.wikiInfoAdapter!!.clear()

        wikiInfoList.isEmpty().apply {
            mBinding.wikiInfoAdapter?.clear()
            lw.emptyView = emptyText
            emptyText.visibility = View.VISIBLE
        }.not().apply {
            emptyText.visibility = View.GONE
            mBinding.wikiInfoAdapter?.clear()
            wikiInfoList.forEach { mBinding.wikiInfoAdapter?.add(it) }
            mBinding.wikiInfoAdapter?.notifyDataSetChanged()
        }


        if (wikiInfoList.isEmpty())
        {
            mBinding.wikiInfoAdapter?.clear()
            lw.emptyView = emptyText
            emptyText.visibility = View.VISIBLE
        } else
        {
            emptyText.visibility = View.GONE
            mBinding.wikiInfoAdapter?.clear()
            wikiInfoList.forEach { mBinding.wikiInfoAdapter?.add(it) }
            mBinding.wikiInfoAdapter?.notifyDataSetChanged()
        }
    }


    private fun initListViewCallback()
    {

        val wikiInfoList = mServiceHelper.getAllWikiInfo()
        Handler(Looper.getMainLooper()).post { updateUI(wikiInfoList) }
    }

    private fun initData()
    {
        mBinding.viewModel = SavedWikiInfoActivityViewModel(this)
        mBinding.wikiInfoAdapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, ArrayList())
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