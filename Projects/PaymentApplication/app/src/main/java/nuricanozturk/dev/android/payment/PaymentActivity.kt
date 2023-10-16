package nuricanozturk.dev.android.payment

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import nuricanozturk.dev.android.app.data.service.dto.LoginInfoDTO
import nuricanozturk.dev.android.payment.databinding.ActivityPaymentBinding
import nuricanozturk.dev.android.payment.global.LOGIN_INFO
import nuricanozturk.dev.android.payment.viewmodel.PaymentActivityListenerViewModel


class PaymentActivity : AppCompatActivity()
{
    private lateinit var mBinding : ActivityPaymentBinding
        private lateinit var mLoginInfo: LoginInfoDTO
    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }
    private fun initLoginInfo() {
        mLoginInfo =
            if (Build.VERSION.SDK_INT < 33) intent.getSerializableExtra(LOGIN_INFO) as LoginInfoDTO
            else intent.getSerializableExtra(LOGIN_INFO, LoginInfoDTO::class.java)!!
    }
    private fun initialize()
    {
        initLoginInfo()
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
                                    .apply { putExtra(LOGIN_INFO, mLoginInfo); startActivity(this) }
}