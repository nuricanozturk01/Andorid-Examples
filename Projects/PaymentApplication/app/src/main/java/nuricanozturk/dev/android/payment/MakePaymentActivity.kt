package nuricanozturk.dev.android.payment

import android.os.Build
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
import nuricanozturk.dev.android.app.data.service.dto.LoginInfoDTO
import nuricanozturk.dev.android.app.data.service.dto.PaymentSaveDTO
import nuricanozturk.dev.android.payment.databinding.ActivityMakePaymentBinding
import nuricanozturk.dev.android.payment.global.LOGIN_INFO
import nuricanozturk.dev.android.payment.viewmodel.MakePaymentActivityListenerViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MakePaymentActivity : AppCompatActivity()
{
    private lateinit var mBinding : ActivityMakePaymentBinding
    private lateinit var mLoginInfo: LoginInfoDTO

    @Inject
    lateinit var mService : PaymentAppDataService

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
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_make_payment)
        mBinding.viewModel = MakePaymentActivityListenerViewModel(this)
        mBinding.paymentSaveDTO = PaymentSaveDTO(username = mLoginInfo.username)
        mBinding.result = ""
    }


    fun makePaymentButtonClicked()
    {
        try
        {
            if (checkEmptyFields()) return

            mService.makePayment(mBinding.paymentSaveDTO!!)
            mBinding.result = mBinding.paymentSaveDTO!!.toString()
            Toast.makeText(this, "username: ${mBinding.paymentSaveDTO!!.username}", Toast.LENGTH_LONG).show()
        }
        catch (ex : DataServiceException)
        {
            Toast.makeText(this, "Data Problem: ${ex.message}", Toast.LENGTH_LONG).show()
        }
        catch (ex : Throwable)
        {
            Toast.makeText(this, "Problem occured: ${ex.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkEmptyFields() : Boolean
    {
        val paymentDTO = mBinding.paymentSaveDTO!!

        if (paymentDTO.description.isBlank() || paymentDTO.description.isEmpty() || paymentDTO.quantity <= 0.0)
        {
            AlertDialog.Builder(this)
                .setTitle(resources.getString(R.string.alert_dialog_warning_title))
                .setMessage(resources.getString(R.string.alert_dialog_not_empty_fields_text))
                .setPositiveButton(resources.getString(R.string.alert_dialog_register_close_text)) { _, _ -> }
                .create().show()
            return true
        }

        return false
    }

    fun clearButtonClicked()
    {
        AlertDialog.Builder(this).setTitle(resources.getString(R.string.alert_dialog_clear_title))
            .setPositiveButton(resources.getString(R.string.alert_dialog_register_yes_text)) { _, _ -> clearPositiveButtonCallback() }
            .setNegativeButton(resources.getString(R.string.alert_dialog_register_no_text)) { _, _ -> }
            .create().show()
    }

    private fun clearPositiveButtonCallback()
    {
        mBinding.paymentSaveDTO = PaymentSaveDTO()
        mBinding.result = ""
        mBinding.paymentActivityLayout.children.filterIsInstance<EditText>()
            .firstOrNull { it.hint == resources.getString(R.string.edittext_description_hint) }
            ?.requestFocus()
    }

    fun closeButtonClicked() = finish()
}