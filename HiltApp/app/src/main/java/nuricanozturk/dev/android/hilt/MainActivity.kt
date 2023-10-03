package nuricanozturk.dev.android.hilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import nuricanozturk.dev.android.hilt.calculator.IBinaryOperation
import nuricanozturk.dev.android.hilt.calculator.di.module.annotation.IntAddOperationInterceptor
import nuricanozturk.dev.android.hilt.databinding.ActivityMainBinding
import nuricanozturk.dev.android.hilt.datetime.DateInfo
import nuricanozturk.dev.android.hilt.datetime.DateTimeInfo
import nuricanozturk.dev.android.hilt.datetime.TimeInfo
import nuricanozturk.dev.android.hilt.viewmodel.MainActivityViewModel
import nuricanozturk.dev.android.hilt.viewmodel.OperationInfo
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    private lateinit var mBinding : ActivityMainBinding

    @Inject
    lateinit var dateTimeInfo: DateTimeInfo // Field Injection

    @Inject
    lateinit var dateInfo: DateInfo

    @Inject
    lateinit var timeInfo: TimeInfo

    @Inject
    @IntAddOperationInterceptor
    lateinit var intBinaryOperation : IBinaryOperation<Int>
    private fun showDateTime()
    {
        val sb = StringBuilder()

        sb.append("DateTime:").append(dateTimeInfo.toString()).append("\n")
            .append("Date:").append(dateInfo.toString()).append("\n")
            .append("Time:").append(timeInfo.toString())

        Toast.makeText(this, sb, Toast.LENGTH_LONG).show()
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.viewModel = MainActivityViewModel(this)
        mBinding.operationInfo = OperationInfo()
    }

    private fun initialize()
    {
        initBinding()
    }
    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    override fun onResume()
    {
        super.onResume()
        showDateTime()
    }

    fun handleCalculateButton()
    {
        if (intBinaryOperation.isValid(mBinding.operationInfo!!.op))
            mBinding.result = intBinaryOperation.applyAsInt(mBinding.operationInfo!!.first,
                                                            mBinding.operationInfo!!.second).toString()

        else mBinding.result = "Invalid Operation!"
    }

}