package nuricanozturk.dev.android.multipleactivity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import nuricanozturk.dev.android.multipleactivity.databinding.ActivityRegisterBinding
import nuricanozturk.dev.android.multipleactivity.viewmodel.RegisterInfo

class RegisterActivity : AppCompatActivity()
{
    private lateinit var mBinding: ActivityRegisterBinding

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initViewModels()
    {
        mBinding.registerInfo = RegisterInfo()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        initViewModels()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun initialize()
    {
        initBinding()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    fun registerButtonClicked()
    {
        TODO("Not implemented yet!...")
    }

    fun clearButtonClicked()
    {
        TODO("Not implemented yet!...")
    }

    fun closeButtonClicked()
    {
        TODO("Not implemented yet!...")
    }
}