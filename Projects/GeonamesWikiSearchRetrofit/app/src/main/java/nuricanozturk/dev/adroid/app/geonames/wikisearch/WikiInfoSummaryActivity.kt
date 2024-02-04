package nuricanozturk.dev.adroid.app.geonames.wikisearch

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import nuricanozturk.dev.adroid.app.geonames.wikisearch.api.WikiInfo
import nuricanozturk.dev.adroid.app.geonames.wikisearch.databinding.ActivityWikiInfoSummaryBinding
import nuricanozturk.dev.adroid.app.geonames.wikisearch.global.WIKI_INFO

@AndroidEntryPoint
class WikiInfoSummaryActivity : AppCompatActivity()
{
    private lateinit var mBinding : ActivityWikiInfoSummaryBinding
    private lateinit var mWikiInfo : WikiInfo

    private fun initWikiInfo()
    {
        mWikiInfo =
            if (Build.VERSION.SDK_INT < 33) intent.getSerializableExtra(WIKI_INFO) as WikiInfo
            else intent.getSerializableExtra(WIKI_INFO, WikiInfo::class.java)!!
    }


    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    private fun initialize()
    {
        initBinding()
        initWikiInfo()
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_wiki_info_summary)
        initWikiInfo()
        initData()
    }

    private fun initData()
    {
        mBinding.selectedWikiInfo = mWikiInfo
    }
}