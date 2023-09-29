package nuricanozturk.dev.android.multipleactivity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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

const val PAYMENT_ACTIVITY_REQUEST_CODE = 1

class LoginActivity : AppCompatActivity()
{

    lateinit var mBinding : ActivityLoginBinding

    private fun initialize()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        mBinding.viewModel = LoginActivityViewModel(this)
        mBinding.loginInfoViewModel = LoginInfoViewModel()
    }

    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun handleLoginButton()
    {
        val now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"))
            .toString()

        Toast.makeText(this, now, Toast.LENGTH_LONG).show()

        Intent(this, PaymentActivity::class.java).apply {
            startActivityForResult(putExtra(USER_INFO, mBinding.loginInfoViewModel), PAYMENT_ACTIVITY_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode : Int, resultCode : Int, data : Intent?)
    {
        if (requestCode == PAYMENT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK)
        {
            val productName = data?.getStringExtra(PRODUCT_NAME)
            val totalPrice = data?.getDoubleExtra(TOTAL_PRICE, 0.0)

            "$totalPrice paid for '$productName'".apply {
                Toast.makeText(this@LoginActivity, this, Toast.LENGTH_LONG).show()
            }

        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}