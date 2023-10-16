package nuricanozturk.dev.android.payment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import nuricanozturk.dev.android.payment.databinding.ActivityPaymentBinding
import nuricanozturk.dev.android.payment.viewmodel.PaymentActivityListenerViewModel


class PaymentActivity : AppCompatActivity()
{
    private lateinit var mBinding : ActivityPaymentBinding

    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    private fun initialize()
    {
        initBinding()
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_payment)
        mBinding.viewModel = PaymentActivityListenerViewModel(this)
    }

    fun closeButtonClicked() = finish()
    fun paymentsButtonClicked() = Intent(this, PaymentsActivity::class.java)
                                    .apply { startActivity(this) }

    fun makePaymentButtonClicked() = Intent(this, MakePaymentActivity::class.java)
                                    .apply { startActivity(this) }
}