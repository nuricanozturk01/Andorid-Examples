package nuricanozturk.dev.android.payment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import nuricanozturk.dev.android.app.data.service.PaymentAppDataService
import nuricanozturk.dev.android.app.data.service.dto.LoginInfoDTO
import nuricanozturk.dev.android.payment.databinding.ActivityOperationsBinding
import nuricanozturk.dev.android.payment.global.LOGIN_INFO
import nuricanozturk.dev.android.payment.viewmodel.OperationsActivityViewModel
import javax.inject.Inject

@AndroidEntryPoint
class OperationsActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityOperationsBinding;

    private lateinit var mLoginInfo: LoginInfoDTO

    @Inject
    lateinit var mService: PaymentAppDataService

    private fun initLoginInfo()
    {
        mLoginInfo =
            if (Build.VERSION.SDK_INT < 33) intent.getSerializableExtra(LOGIN_INFO) as LoginInfoDTO
            else intent.getSerializableExtra(LOGIN_INFO, LoginInfoDTO::class.java)!!
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_operations)
        mBinding.viewModel = OperationsActivityViewModel(this)
    }

    private fun initialize()
    {
        initBinding()
        initLoginInfo()
    }

    fun paymentButtonClick()
    {
        Intent(this, PaymentActivity::class.java)
            .apply {putExtra(LOGIN_INFO, mLoginInfo); startActivity(this) }
    }

    fun loginInformationButtonClick()
    {
        Intent(this, LoginInformationActivity::class.java)
            .apply {putExtra(LOGIN_INFO, mLoginInfo); startActivity(this) }
    }

    fun closeButtonClick()
    {
        finish()
    }
}