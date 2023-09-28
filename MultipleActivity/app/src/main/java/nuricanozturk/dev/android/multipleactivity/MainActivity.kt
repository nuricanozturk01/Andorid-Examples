package nuricanozturk.dev.android.multipleactivity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import nuricanozturk.dev.android.multipleactivity.databinding.ActivityMainBinding
import nuricanozturk.dev.android.multipleactivity.keys.DATE
import nuricanozturk.dev.android.multipleactivity.keys.DATE_TIME
import nuricanozturk.dev.android.multipleactivity.viewmodel.MainActivityViewModel
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity()
{

    private lateinit var mBinding : ActivityMainBinding
    private fun initialize()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.viewModel = MainActivityViewModel(this)
    }

    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    fun registerButtonClick()
    {
        startActivity(Intent(this, RegisterActivity::class.java))
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun loginButtonClick()
    {
        startActivity(Intent(this, LoginActivity::class.java).apply {
            putExtra(DATE_TIME, DATE)
        })
    }

   /* override fun onDestroy()
    {
        mBinding.viewModel = null // Solution 1
        super.onDestroy()
    }*/

    fun logoutButtonClick()
    {
        finish()
    }
}