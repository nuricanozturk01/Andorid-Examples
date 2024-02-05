package nuricanozturk.dev.adroid.app.geonames.wikisearch

import android.app.AlertDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import nuricanozturk.dev.adroid.app.geonames.repositorylib.dal.WikiSearchServiceHelper
import nuricanozturk.dev.adroid.app.geonames.repositorylib.entity.QueryInfo
import nuricanozturk.dev.adroid.app.geonames.wikisearch.api.WikiInfo
import nuricanozturk.dev.adroid.app.geonames.wikisearch.databinding.ActivityWikiInfoSummaryBinding
import nuricanozturk.dev.adroid.app.geonames.wikisearch.exception.WikiInfoNotFoundException
import nuricanozturk.dev.adroid.app.geonames.wikisearch.global.QUERY
import nuricanozturk.dev.adroid.app.geonames.wikisearch.global.WIKI_INFO
import nuricanozturk.dev.adroid.app.geonames.wikisearch.viewmodel.WikiInfoSummaryViewModel
import java.time.LocalDateTime
import java.util.concurrent.ScheduledExecutorService
import javax.inject.Inject
import nuricanozturk.dev.adroid.app.geonames.repositorylib.entity.WikiInfo as WikiInfoEntity

@AndroidEntryPoint
class WikiInfoSummaryActivity : AppCompatActivity()
{
    private lateinit var mBinding : ActivityWikiInfoSummaryBinding
    private lateinit var mWikiInfo : WikiInfo
    private lateinit var mQuery : String

    @Inject
    lateinit var mExecutorService : ScheduledExecutorService

    @Inject
    lateinit var mWikiSearchServiceHelper : WikiSearchServiceHelper
    private fun initWikiInfo()
    {
        mWikiInfo =
            if (Build.VERSION.SDK_INT < 33) intent.getSerializableExtra(WIKI_INFO) as WikiInfo
            else intent.getSerializableExtra(WIKI_INFO, WikiInfo::class.java)!!

        mQuery = if (Build.VERSION.SDK_INT < 33) intent.getStringExtra(QUERY) as String
        else intent.getStringExtra(QUERY)!!
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
        mBinding.viewModel = WikiInfoSummaryViewModel(this)
    }

    fun handleSaveButtonClicked()
    {
        AlertDialog.Builder(this).setTitle(R.string.alertdialog_save_title_text)
            .setMessage(R.string.alertdialog_save_message_text)
            .setPositiveButton(R.string.alertdialog_save_yes_button_text) { _, _ -> mExecutorService.execute { saveWikiInfo(mWikiInfo) } }
            .setNegativeButton(R.string.alertdialog_save_no_button_text) { _, _ -> finish() }
            .create().show()
    }

    private fun saveWikiInfo(wikiInfo : WikiInfo?)
    {
        val q = mQuery + "-" + wikiInfo?.title
        val queryInfo = QueryInfo(query = q, lastQueryTime = LocalDateTime.now())
        mWikiSearchServiceHelper.save(queryInfo)

        val wi =
            WikiInfoEntity(query = q, summary = wikiInfo?.summary!!, title = wikiInfo.title!!, longitude = wikiInfo.latitude, latitude = wikiInfo.longitude, countryCode = wikiInfo.countryCode)

        mWikiSearchServiceHelper.save(wi)

        runOnUiThread {
            Toast.makeText(this@WikiInfoSummaryActivity, R.string.wikiinfo_saved_text, Toast.LENGTH_SHORT)
                .show()
        }
    }

    fun handleRemoveButtonClicked()
    {
        AlertDialog.Builder(this).setTitle(R.string.alertdialog_remove_title_text)
            .setMessage(R.string.alertdialog_remove_message_text)
            .setPositiveButton(R.string.alertdialog_remove_button_text) { _, _ -> mExecutorService.execute { removeWikiInfo(mWikiInfo) } }
            .setNegativeButton(R.string.alertdialog_save_no_button_text) { _, _ -> finish() }
            .create().show()
    }

    private fun removeWikiInfo(wikiInfo : WikiInfo)
    {
        try
        {
            mWikiSearchServiceHelper.getWikiInfoByQuery(mQuery)
                ?: throw WikiInfoNotFoundException("WikiInfo not found")
            mWikiSearchServiceHelper.removeQueryInfo(mQuery)
            runOnUiThread {
                Toast.makeText(this@WikiInfoSummaryActivity, R.string.wikiinfo_removed_text, Toast.LENGTH_SHORT)
                    .show()
            }
            finish()


        }
        catch (ex : WikiInfoNotFoundException)
        {
            runOnUiThread {
                Toast.makeText(this@WikiInfoSummaryActivity, ex.message, Toast.LENGTH_SHORT).show()
            }
        }
        catch (e : Exception)
        {
            runOnUiThread {
                Toast.makeText(this@WikiInfoSummaryActivity, R.string.wikiinfo_remove_error_text, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}