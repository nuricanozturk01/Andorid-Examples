package nuricanozturk.dev.android.payment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.karandev.util.data.service.DataServiceException
import dagger.hilt.android.AndroidEntryPoint
import nuricanozturk.dev.android.app.data.service.PaymentAppDataService
import nuricanozturk.dev.android.app.data.service.dto.UserSaveDTO
import nuricanozturk.dev.android.app.data.service.mapper.IUserSaveMapper
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
                Toast.makeText(this, "${user.username} successfully registered", Toast.LENGTH_LONG).show()
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
}