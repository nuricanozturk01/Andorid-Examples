package nuricanozturk.dev.android.basicviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import nuricanozturk.dev.android.basicviews.viewmodel.RegisterInfo
import java.lang.Exception
import java.time.DateTimeException
import java.time.LocalDate
import java.time.format.DateTimeParseException

class MainActivity : AppCompatActivity() {
    private lateinit var mButtonClose: Button
    private lateinit var mButtonRegister: Button
    private lateinit var mEditTextName: EditText
    private lateinit var mEditTextEmail: EditText
    private lateinit var mEditTextDay: EditText
    private lateinit var mEditTextMonth: EditText
    private lateinit var mEditTextYear: EditText
    private lateinit var mEditTextPassword: EditText
    private lateinit var mEditTextPasswordAgain: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }

    private fun initEditTexts() {
        mEditTextName = findViewById(R.id.mainActivityEditTextUsername)
        mEditTextEmail = findViewById(R.id.mainActivityEditTextEmail)
        mEditTextDay = findViewById(R.id.mainActivityDay)
        mEditTextMonth = findViewById(R.id.mainActivityMonth)
        mEditTextYear = findViewById(R.id.mainActivityYear)
        mEditTextPassword = findViewById(R.id.mainActivityPassword)
        mEditTextPasswordAgain = findViewById(R.id.mainActivityPasswordAgain)
    }

    private fun registerButtonCallback() {
        try {
            val password = mEditTextPassword.text.toString()
            val passwordAgain = mEditTextPasswordAgain.text.toString()

            if (password != passwordAgain || (password.isEmpty() || password.isBlank() || passwordAgain.isBlank() || passwordAgain.isEmpty()))
                throw Exception()
            val birthDate = LocalDate.of(
                mEditTextYear.text.toString().toInt(),
                mEditTextMonth.text.toString().toInt(),
                mEditTextDay.text.toString().toInt()
            )
            val registerInfo = RegisterInfo(
                mEditTextName.text.toString(),
                mEditTextEmail.text.toString(),
                birthDate
            )

            Toast.makeText(this, registerInfo.toString(), Toast.LENGTH_LONG).show()

        } catch (ignore: NumberFormatException) {
            Toast.makeText(this, "Invalid value format", Toast.LENGTH_LONG).show()
        } catch (ignore: DateTimeException) {
            Toast.makeText(this, "Invalid date format", Toast.LENGTH_LONG).show()
        } catch (ignore: Exception) {
            Toast.makeText(this, "Passwords are not same!", Toast.LENGTH_LONG).show()
        }
    }


    private fun initialize() {
        initViews()
    }

    private fun initViews() {
        initEditTexts()
        initCloseButton()
        initAcceptCheckbox()
        initRegisterButton()
    }

    private fun initAcceptCheckbox() {
        mButtonClose = findViewById<CheckBox>(R.id.checkBox)
            .apply {
                setOnCheckedChangeListener { _, checked ->
                    mButtonRegister.isEnabled = checked
                }
            }
    }

    private fun initCloseButton() {
        mButtonClose =
            findViewById<Button>(R.id.mainActivityCloseButton).apply { setOnClickListener { finish() } }// destroy
    }

    private fun initRegisterButton() {

        mButtonRegister =
            findViewById<Button>(R.id.mainActivityRegisterButton).apply { setOnClickListener { registerButtonCallback() } }
    }
}