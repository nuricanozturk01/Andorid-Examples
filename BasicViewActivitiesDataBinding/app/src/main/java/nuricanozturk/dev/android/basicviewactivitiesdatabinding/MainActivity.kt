package nuricanozturk.dev.android.basicviewactivitiesdatabinding

import nuricanozturk.dev.android.basicviewactivitiesdatabinding.viewmodel.RegisterInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.children
import androidx.core.view.isInvisible
import androidx.databinding.DataBindingUtil
import nuricanozturk.dev.android.basicviewactivitiesdatabinding.alert.INPUT_TYPE_TEXT_PASSWORD_HIDE
import nuricanozturk.dev.android.basicviewactivitiesdatabinding.alert.INPUT_TYPE_TEXT_PASSWORD_SHOW
import nuricanozturk.dev.android.basicviewactivitiesdatabinding.alert.promptDecision
import nuricanozturk.dev.android.basicviewactivitiesdatabinding.alert.promptNotConfirmedDialog
import nuricanozturk.dev.android.basicviewactivitiesdatabinding.databinding.ActivityMainBinding
import nuricanozturk.dev.android.basicviewactivitiesdatabinding.viewmodel.MainActivityViewModel

import java.time.DateTimeException
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    private lateinit var mDataBinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }

    private fun initBirthDateTexts() {
        val today = LocalDate.now()

        mDataBinding.day = today.dayOfMonth.toString()
        mDataBinding.month = today.monthValue.toString()
        mDataBinding.year = today.year.toString()
    }

    private fun initBinding() {

        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mDataBinding.registerInfoViewModel = RegisterInfo()
        mDataBinding.mainActivityViewModel = MainActivityViewModel(this)

        initBirthDateTexts()
        mDataBinding.show = true
        mDataBinding.passwordInputType = INPUT_TYPE_TEXT_PASSWORD_HIDE
        mDataBinding.showPasswordButtonText = resources.getString(R.string.show_password_button)


    }

    private fun showPasswordButtonClickedCallback() {
        val show = mDataBinding.show as Boolean

        val resId = if (show) R.string.hide_password_button else R.string.show_password_button

        mDataBinding.showPasswordButtonText = resources.getString(resId)

        mDataBinding.passwordInputType =
            if (show) INPUT_TYPE_TEXT_PASSWORD_SHOW else INPUT_TYPE_TEXT_PASSWORD_HIDE

        mDataBinding.show = !show
    }


    private fun registerButtonCallback(): RegisterInfo? {
        val registerInfo: RegisterInfo?
        try {
            val password = mDataBinding.password!!
            val passwordAgain = mDataBinding.confirmPassword!!


            if (!doForConfirmation(password, passwordAgain))
                return null;
            val today = LocalDate.now()
            mDataBinding.day = today.dayOfMonth.toString()
            mDataBinding.month = today.monthValue.toString()
            mDataBinding.year = today.year.toString()

            registerInfo = RegisterInfo(
                mDataBinding.mainActivityEditTextName.text.toString(),
                mDataBinding.registerInfoViewModel!!.email,
                LocalDate.of(
                    mDataBinding.year!!.toInt(),
                    mDataBinding.month!!.toInt(),
                    mDataBinding.day!!.toInt()
                )
            )
            mDataBinding.result = registerInfo.toString()
            Toast.makeText(this, "Welcome $registerInfo", Toast.LENGTH_LONG).show()
            return registerInfo

        } catch (ignore: NumberFormatException) {
            Toast.makeText(this, "Invalid value format", Toast.LENGTH_LONG).show()
        } catch (ignore: DateTimeException) {
            Toast.makeText(this, "Invalid date format", Toast.LENGTH_LONG).show()
        }
        return null
    }

    private fun doForConfirmation(password: String, passwordAgain: String): Boolean {
        var result = true
        if (password != passwordAgain || (password.isEmpty() || password.isBlank() || passwordAgain.isBlank() || passwordAgain.isEmpty())) {
            promptNotConfirmedDialog(this, "Alert", "Password Not Confirmed",
                "OK", { _, _ -> neutralButtonCallback() });
            Toast.makeText(this, "Passwords are not same!", Toast.LENGTH_LONG).show()
            result = false;
        }
        return result;
    }

     fun openToggleButtonCallback(checked : Boolean)
    {
        setVisibility(if (checked) View.VISIBLE else View.GONE)
    }
    private fun neutralButtonCallback() {
        mDataBinding.registerInfoViewModel?.password = ""
        mDataBinding.confirmPassword = ""
    }

    private fun initialize() {
        initBinding()
    }


    private fun clearEditTexts() {
        mDataBinding.mainActivityEditTextName.setText("")
        mDataBinding.mainActivityEmailEditText.setText("")
        mDataBinding.password = ""
        mDataBinding.confirmPassword = ""


        mDataBinding.day = ""
        mDataBinding.month = ""
        mDataBinding.year = ""

        initBirthDateTexts()

        mDataBinding.result = ""
    }

    private fun setVisibility(visibility : Int)
    {
        clearButtonCallback()
        for (view in mDataBinding.mainActivityLayoutRegisterInfo.children)
            view.visibility = visibility
    }
    private fun clearButtonCallback() {
        clearEditTexts()
        mDataBinding.accept = false
        mDataBinding.passwordInputType = INPUT_TYPE_TEXT_PASSWORD_HIDE
        mDataBinding.showPasswordButtonText = resources.getString(R.string.show_password_button)
        mDataBinding.show = false;

        mDataBinding.mainActivityEditTextName.requestFocus()
    }


    private fun closeButtonClickCallback() {
        promptDecision(this, "Warning", "Are you sure?", "Yes", "No",
            { _, _ -> positiveButtonCallback() }) { _, _ -> negativeButtonCallback() }
    }

    private fun positiveButtonCallback() = finish()

    private fun negativeButtonCallback() =
        Toast.makeText(this, "Continue the program!", Toast.LENGTH_LONG).show()

    fun handleShowPasswordButton() = showPasswordButtonClickedCallback()

    fun handleRegisterButton() = registerButtonCallback()

    fun handleClearButton() = clearButtonCallback()

    fun handleCloseButton() = closeButtonClickCallback()

}