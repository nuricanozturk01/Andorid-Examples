package nuricanozturk.dev.android.multipleactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import nuricanozturk.dev.android.multipleactivity.databinding.ActivityPaymentBinding
import nuricanozturk.dev.android.multipleactivity.keys.USERNAME
import nuricanozturk.dev.android.multipleactivity.viewmodel.PaymentActivityViewModel

class PaymentActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityPaymentBinding

    private fun initData()
    {
        mBinding.viewModel!!.username = intent.getStringExtra(USERNAME)!!

    }
    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_payment)
        mBinding.viewModel = PaymentActivityViewModel(this)
    }
    private fun initialize() {
        initBinding()
        initData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

}