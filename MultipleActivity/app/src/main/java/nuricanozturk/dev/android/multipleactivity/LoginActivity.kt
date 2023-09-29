package nuricanozturk.dev.android.multipleactivity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import nuricanozturk.dev.android.multipleactivity.databinding.ActivityLoginBinding
import nuricanozturk.dev.android.multipleactivity.keys.PRODUCT_NAME
import nuricanozturk.dev.android.multipleactivity.keys.TOTAL_PRICE
import nuricanozturk.dev.android.multipleactivity.keys.USER_INFO
import nuricanozturk.dev.android.multipleactivity.viewmodel.LoginActivityViewModel
import nuricanozturk.dev.android.multipleactivity.viewmodel.LoginInfoViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class LoginActivity : AppCompatActivity()
{

    private lateinit var mBinding : ActivityLoginBinding
    private lateinit var mLauncher : ActivityResultLauncher<Intent>
    private fun initialize()
    {

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        mBinding.viewModel = LoginActivityViewModel(this)
        mBinding.loginInfoViewModel = LoginInfoViewModel()
    }

    private fun paymentActivityCallback(result : ActivityResult)
    {
        if (result.resultCode != RESULT_OK) return

        val data = result.data
        val productName = data?.getStringExtra(PRODUCT_NAME)
        val totalPrice = data?.getDoubleExtra(TOTAL_PRICE, 0.0)

        "$totalPrice paid for '$productName'".apply {
            Toast.makeText(this@LoginActivity, this, Toast.LENGTH_LONG).show()
        }
    }

    private fun initLauncher()
    {
        mLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            paymentActivityCallback(it)
        }
    }


    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
        initLauncher()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun handleLoginButton()
    {
        val now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"))
            .toString()

        Toast.makeText(this, now, Toast.LENGTH_LONG).show()

        Intent(this, PaymentActivity::class.java).apply {
            putExtra(USER_INFO, mBinding.loginInfoViewModel)
            mLauncher.launch(this)
        }
    }

}