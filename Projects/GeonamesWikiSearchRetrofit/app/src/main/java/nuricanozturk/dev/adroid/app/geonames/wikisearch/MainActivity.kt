package nuricanozturk.dev.adroid.app.geonames.wikisearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import nuricanozturk.dev.adroid.app.geonames.wikisearch.databinding.ActivityMainBinding
import nuricanozturk.dev.adroid.app.geonames.wikisearch.viewmodel.MainActivityViewModel


class MainActivity : AppCompatActivity()
{
    private lateinit var mBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    private fun initialize()
    {
        initBinding()
        initData()
    }

    private fun initData()
    {
        mBinding.viewModel = MainActivityViewModel(this)
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    fun handleSearchButtonClicked()
    {
        Intent(this, WikiInfoSearchActivity::class.java).apply {
            startActivity(this)
        }
    }

    fun handleSavedWikiInfosButtonClicked()
    {
        Intent(this, SavedWikiInfoActivity::class.java).apply {
            startActivity(this)
        }
    }
}