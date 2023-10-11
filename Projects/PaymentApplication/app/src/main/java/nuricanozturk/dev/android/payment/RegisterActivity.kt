package nuricanozturk.dev.android.payment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import com.karandev.util.data.service.DataServiceException
import dagger.hilt.android.AndroidEntryPoint
import nuricanozturk.dev.android.app.data.service.PaymentAppDataService
import nuricanozturk.dev.android.app.data.service.dto.UserSaveDTO
import nuricanozturk.dev.android.payment.databinding.ActivityRegisterBinding
import nuricanozturk.dev.android.payment.viewmodel.RegisterActivityListenerViewModel
import javax.inject.Inject

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity()
{
    private lateinit var mBinding: ActivityRegisterBinding

    @Inject
    lateinit var mPaymentService : PaymentAppDataService

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        mBinding.viewModel = RegisterActivityListenerViewModel(this)
        mBinding.user = UserSaveDTO()
        mBinding.isFocus = false
    }
    private fun registerSuccessCallback()
    {
        val user = mBinding.user!!
        Toast.makeText(this, "${user.username} successfully registered", Toast.LENGTH_LONG).show()
        Intent(this, LoginActivity::class.java).apply { startActivity(this) }
        finish()

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


    fun registerButtonClicked()
    {
        try
        {
            val user = mBinding.user!!

            if (mPaymentService.saveUser(user))
                registerSuccessCallback()
            else
                Toast.makeText(this, "${user.username} cannot be registered", Toast.LENGTH_LONG).show()
        }
        catch (ex: DataServiceException)
        {
            Toast.makeText(this, "Data Problem: ${ex.message}", Toast.LENGTH_LONG).show()
        }
        catch (ex: Throwable)
        {
            Toast.makeText(this, "Problem occured: ${ex.message}", Toast.LENGTH_LONG).show()
        }
    }


    fun closeButtonClicked()
    {
        AlertDialog.Builder(this)
            .setTitle(R.string.alert_dialog_register_close_title_text)
            .setPositiveButton(R.string.alert_dialog_register_yes_text){_,_ -> finish()}
            .setNegativeButton(R.string.alert_dialog_register_no_text){_,_ ->}
            .create()
            .show()

        finish()
    }

    fun clearButtonClicked()
    {
        mBinding.user = UserSaveDTO()

        mBinding.registerActivityLayout.children
            .filterIsInstance<EditText>()
            .firstOrNull{it.hint == resources.getString(R.string.edittext_user_name_hint_text)}
            ?.requestFocus()
    }
}