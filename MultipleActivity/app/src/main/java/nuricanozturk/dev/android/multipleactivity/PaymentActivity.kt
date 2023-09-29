package nuricanozturk.dev.android.multipleactivity

import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

import nuricanozturk.dev.android.multipleactivity.databinding.ActivityPaymentBinding
import nuricanozturk.dev.android.multipleactivity.keys.PRODUCT_NAME
import nuricanozturk.dev.android.multipleactivity.keys.TOTAL_PRICE
import nuricanozturk.dev.android.multipleactivity.keys.USER_INFO
import nuricanozturk.dev.android.multipleactivity.viewmodel.LoginInfoViewModel
import nuricanozturk.dev.android.multipleactivity.viewmodel.PaymentActivityViewModel
import nuricanozturk.dev.android.multipleactivity.viewmodel.PaymentInfo
import nuricanozturk.dev.app.multipleactivity.library.databinding.binding.PaymentInfoUnitPriceToString
import nuricanozturk.dev.app.multipleactivity.library.databinding.binding.PaymentQuantityToStringConverter

class PaymentActivity : AppCompatActivity()
{

    private lateinit var mBinding : ActivityPaymentBinding

    private fun initViewModels()
    {
        mBinding.viewModel = PaymentActivityViewModel(this)
        mBinding.loginInfoViewModel = when
        {
            Build.VERSION.SDK_INT < VERSION_CODES.TIRAMISU -> intent.getSerializableExtra(USER_INFO) as LoginInfoViewModel
            else -> intent.getSerializableExtra(USER_INFO, LoginInfoViewModel::class.java)
        }
        mBinding.paymentInfo = PaymentInfo()
        mBinding.result = ""
        PaymentQuantityToStringConverter.failStr = "Invalid quantity"
        PaymentInfoUnitPriceToString.failStr = "Invalid unit price"
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_payment)
        initViewModels()
    }

    private fun initialize() = initBinding()

    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
        Toast.makeText(this, mBinding.loginInfoViewModel!!.password, Toast.LENGTH_LONG).show()
    }

    private fun checkFailListAppCallback(list : MutableList<String>) : List<String>
    {
        if (PaymentQuantityToStringConverter.fail) list.add(PaymentQuantityToStringConverter.failStr)

        if (PaymentInfoUnitPriceToString.fail) list.add(PaymentInfoUnitPriceToString.failStr)

        if (list.isNotEmpty()) Toast.makeText(this, list.joinToString("\n"), Toast.LENGTH_LONG)
            .show()

        return list
    }

    private fun checkFail() = ArrayList<String>().apply { checkFailListAppCallback(this) }

    fun payButtonClicked()
    {
        mBinding.result = ""
        if (checkFail().isNotEmpty()) return

        mBinding.result = mBinding.paymentInfo!!.toString()
    }

    fun exitButtonClicked() = AlertDialog.Builder(this).setTitle(R.string.close_question_title)
        .setMessage(R.string.close_message_body)
        .setPositiveButton(R.string.close_yes_button) { d, w -> positiveButtonCallbackExit(d, w) }
        .setNegativeButton(R.string.close_no_button) { _, _ -> {} }.create().show()

    private fun positiveButtonCallbackExit(d : DialogInterface?, w : Int)
    {
        if (mBinding.result!!.isNotEmpty())
        {
            Intent().apply {
                putExtra(PRODUCT_NAME, mBinding.paymentInfo!!.productName)
                putExtra(TOTAL_PRICE, mBinding.paymentInfo!!.total)
                setResult(RESULT_OK, this)
            }
        }
        finish()
    }

    fun clearButtonClicked()
    {
        AlertDialog.Builder(this).setTitle(R.string.clear_question_title)
            .setMessage(R.string.clear_message_body)
            .setPositiveButton(R.string.clear_yes_button) { _, _ -> clearFields() }
            .setNegativeButton(R.string.clear_no_button) { _, _ -> {} }.create().show()
    }

    private fun clearFields()
    {
        mBinding.activityPaymentEditTextName.text.clear()
        mBinding.activityPaymentTextViewResult.text = ""
        mBinding.paymentInfo = PaymentInfo()
    }

    fun exitSystemButtonClicked()
    {
        TODO("NOT IMPLEMENTED YET")
    }

}