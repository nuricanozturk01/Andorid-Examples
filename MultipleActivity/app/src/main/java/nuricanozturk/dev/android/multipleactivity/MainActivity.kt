package nuricanozturk.dev.android.multipleactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import nuricanozturk.dev.android.multipleactivity.databinding.ActivityMainBinding
import nuricanozturk.dev.android.multipleactivity.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityMainBinding
    private fun initialize()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.viewModel = MainActivityViewModel(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    fun registerButtonClick()
    {
        startActivity(Intent(this, RegisterActivity::class.java))
    }


    fun loginButtonClick()
    {
        startActivity(Intent(this, LoginActivity::class.java))
    }


    fun logoutButtonClick()
    {
        finish()
    }
}