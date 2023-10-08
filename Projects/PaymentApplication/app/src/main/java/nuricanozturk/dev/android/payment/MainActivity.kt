package nuricanozturk.dev.android.payment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil


import nuricanozturk.dev.android.payment.databinding.ActivityMainBinding
import nuricanozturk.dev.android.payment.viewmodel.MainActivityListenerViewModel



class MainActivity : AppCompatActivity()
{
    private lateinit var mBinding : ActivityMainBinding


    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)
        initBinding()
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.viewModel = MainActivityListenerViewModel(this)

    }

 /*   private fun saveUser()
    {
        val user = UserSaveDTO("nuricanozturk", "nuri321", "Nuri",
            "ÖZTÜRK", LocalDate.of(1999, Month.JANUARY, 25), "Can")
        paymentService.saveUser(user)
    }*/

    fun registerButtonClicked()
    {
        Intent(this, RegisterActivity::class.java).apply {startActivity(this)}
    }

    fun loginButtonClicked()
    {
        Intent(this, LoginActivity::class.java).apply {startActivity(this)}
    }
}