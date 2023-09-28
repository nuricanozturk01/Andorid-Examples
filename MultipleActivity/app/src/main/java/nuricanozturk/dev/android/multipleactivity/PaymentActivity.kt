package nuricanozturk.dev.android.multipleactivity

import android.content.DialogInterface
import android.os.Build
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import nuricanozturk.dev.android.multipleactivity.databinding.ActivityPaymentBinding
import nuricanozturk.dev.android.multipleactivity.keys.USER_INFO
import nuricanozturk.dev.android.multipleactivity.viewmodel.LoginInfoViewModel
import nuricanozturk.dev.android.multipleactivity.viewmodel.PaymentActivityViewModel
import nuricanozturk.dev.android.multipleactivity.viewmodel.PaymentInfo

class PaymentActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityPaymentBinding

    private fun initViewModels() {
        mBinding.viewModel = PaymentActivityViewModel(this)
        mBinding.loginInfoViewModel = when {
            Build.VERSION.SDK_INT < VERSION_CODES.TIRAMISU -> intent.getSerializableExtra(
                USER_INFO
            ) as LoginInfoViewModel

            else -> intent.getSerializableExtra(
                USER_INFO,
                LoginInfoViewModel::class.java
            ) as LoginInfoViewModel
        }

        mBinding.result = ""
    }

    private fun initBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_payment)
        initViewModels()
    }

    private fun initialize() = initBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
        Toast.makeText(this, mBinding.loginInfoViewModel!!.password, Toast.LENGTH_LONG).show()
    }

    fun payButtonClicked() {

        try {
            mBinding.result = ""
            val paymentInfo = PaymentInfo(
                mBinding.viewModel!!.name, mBinding.viewModel!!.unitPriceStr.toDouble(),
                mBinding.viewModel!!.quantityStr.toInt()
            )

            mBinding.result = paymentInfo.toString()
        } catch (ignore: Throwable) {
            Toast.makeText(this, "Problem occurs...", Toast.LENGTH_LONG).show()
        }
    }

    fun exitButtonClicked() = AlertDialog.Builder(this)
        .setTitle(R.string.close_question_title)
        .setMessage(R.string.close_message_body)
        .setPositiveButton(R.string.close_yes_button) { d, w -> positiveButtonCallbackExit(d, w)}
        .setNegativeButton(R.string.close_no_button) { _, _ -> {}}
        .create()
        .show()

    private fun positiveButtonCallbackExit(d: DialogInterface?, w: Int) {
        mBinding.loginInfoViewModel!!.username = ""
        mBinding.loginInfoViewModel!!.password = ""
        finish()
    }

    fun clearButtonClicked() {
        AlertDialog.Builder(this)
            .setTitle(R.string.clear_question_title)
            .setMessage(R.string.clear_message_body)
            .setPositiveButton(R.string.clear_yes_button) { _, _ -> clearFields() }
            .setNegativeButton(R.string.clear_no_button) { _,_ -> {}}
            .create()
            .show()
    }
    private fun clearFields() {
        mBinding.activityPaymentEditTextName.text.clear()
        mBinding.activityPaymentEditTextQuantity.text.clear()
        mBinding.activityPaymentEditTextUnitPrice.text.clear()
        mBinding.activityPaymentTextViewResult.text = ""
    }

}