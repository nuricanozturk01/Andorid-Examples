package nuricanozturk.dev.android.multipleactivity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
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
        initEducationAdapter()
    }

    private fun initEducationAdapter() {
        val info = resources.getStringArray(R.array.spinner_education_info)
        mBinding.educationAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, info)
        mBinding.educationSelectedPost = 2
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun initialize() = initBinding()

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

    fun handleChangeEducation(pos: Int)
    {
        val selected = mBinding.educationAdapter!!.getItem(pos)

        Toast.makeText(this, selected, Toast.LENGTH_LONG).show()
    }
}