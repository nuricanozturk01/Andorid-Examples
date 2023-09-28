package nuricanozturk.dev.android.multipleactivity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import nuricanozturk.dev.android.multipleactivity.databinding.ActivityLoginBinding
import nuricanozturk.dev.android.multipleactivity.keys.DATE_TIME
import nuricanozturk.dev.android.multipleactivity.keys.USERNAME
import nuricanozturk.dev.android.multipleactivity.keys.USER_INFO
import nuricanozturk.dev.android.multipleactivity.viewmodel.LoginActivityViewModel
import nuricanozturk.dev.android.multipleactivity.viewmodel.LoginInfoViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class LoginActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityLoginBinding

    private fun initialize() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        mBinding.viewModel = LoginActivityViewModel(this)
        mBinding.loginInfoViewModel = LoginInfoViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun handleLoginButton() {
        val now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")).toString()
        Toast.makeText(this, now, Toast.LENGTH_LONG).show()
        Intent(this, PaymentActivity::class.java).apply {
            putExtra(USER_INFO, mBinding.loginInfoViewModel)
            startActivity(this)
        }
    }
}