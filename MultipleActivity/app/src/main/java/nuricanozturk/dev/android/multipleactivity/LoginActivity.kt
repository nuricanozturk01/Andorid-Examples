package nuricanozturk.dev.android.multipleactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import nuricanozturk.dev.android.multipleactivity.databinding.ActivityLoginBinding
import nuricanozturk.dev.android.multipleactivity.keys.USERNAME
import nuricanozturk.dev.android.multipleactivity.viewmodel.LoginActivityViewModel


class LoginActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityLoginBinding

    private fun initialize() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        mBinding.viewModel = LoginActivityViewModel(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    fun handleLoginButton() {
        Intent(this, PaymentActivity::class.java).apply {
            putExtra(USERNAME, mBinding.viewModel!!.username)
            startActivity(this)
        }
    }
}