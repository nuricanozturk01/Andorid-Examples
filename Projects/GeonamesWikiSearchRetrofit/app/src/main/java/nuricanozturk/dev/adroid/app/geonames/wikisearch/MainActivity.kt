package nuricanozturk.dev.adroid.app.geonames.wikisearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import nuricanozturk.dev.adroid.app.geonames.wikisearch.databinding.ActivityMainBinding
import nuricanozturk.dev.adroid.app.geonames.wikisearch.viewmodel.MainActivityViewModel
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.time.LocalDateTime.now
import java.time.format.DateTimeFormatter.ofPattern


class MainActivity : AppCompatActivity()
{
    private lateinit var mBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState : Bundle?)
    {
        val privPref =
            getPreferences(MODE_PRIVATE).getString("LAST_OPEN_DATE_TIME", now().format(ofPattern("dd/MM/yyyy kk:mm:ss")))
        Toast.makeText(this, "Last open: $privPref", Toast.LENGTH_SHORT).show()
        super.onCreate(savedInstanceState)
        initialize()
    }

    override fun onDestroy()
    {
        val activityPref = getPreferences(MODE_PRIVATE).edit()
        activityPref.putString("LAST_OPEN_DATE_TIME", now().format(ofPattern("dd/MM/yyyy kk:mm:ss")))
        activityPref.apply()
        super.onDestroy()
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