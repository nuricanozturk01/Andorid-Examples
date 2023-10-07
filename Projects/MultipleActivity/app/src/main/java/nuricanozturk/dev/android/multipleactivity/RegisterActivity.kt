package nuricanozturk.dev.android.multipleactivity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import nuricanozturk.dev.android.multipleactivity.databinding.ActivityRegisterBinding
import nuricanozturk.dev.android.multipleactivity.viewmodel.RegisterActivityViewModel
import nuricanozturk.dev.android.multipleactivity.viewmodel.RegisterInfo
import java.time.LocalDate

class RegisterActivity : AppCompatActivity()
{
    private lateinit var mBinding : ActivityRegisterBinding
    private lateinit var mMonths : Array<String>

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initViewModels()
    {
        mBinding.registerInfo = RegisterInfo()
        mBinding.viewModel = RegisterActivityViewModel(this)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initBinding()
    {
        mMonths = resources.getStringArray(R.array.months)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        initViewModels()
        initEducationAdapter()
        initBirthDateAdapters()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDaysByMonthAndYear(monthPos : Int, yearPos : Int) : List<Int>
    {
        val max = LocalDate.of(yearPos, monthPos + 1, 1).minusDays(1).dayOfMonth
        Toast.makeText(this, "max is: $max", Toast.LENGTH_LONG).show()
        return (1..max).toList()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initBirthDateTexts(today : LocalDate)
    {
        mBinding.daySelectedPos = today.dayOfMonth - 1
        mBinding.monthSelectedPos = today.monthValue - 1
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initBirthDateAdapters()
    {
        val today = LocalDate.now()
        val years = (today.year - 100..today.year).toList()
        mBinding.dayAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, getDaysByMonthAndYear(today.monthValue, today.year))
        mBinding.monthAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, mMonths)
        mBinding.yearAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, years)
        mBinding.yearSelectedPos = mBinding.yearAdapter!!.count - 1
        initBirthDateTexts(today)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun birthDateMonthSpinnerItemSelected(pos : Int)
    {
        mBinding.dayAdapter?.clear()
        mBinding.dayAdapter?.addAll(getDaysByMonthAndYear(pos + 1, mBinding.yearSelectedPos))
    }

    private fun initEducationAdapter()
    {
        val info = resources.getStringArray(R.array.spinner_education_info)
        mBinding.educationAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, info)
        mBinding.educationSelectedPost = 2
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun initialize() = initBinding()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    fun registerButtonClicked()
    {

        Toast.makeText(this, "Hi! " + mBinding.registerInfo!!.firstName, Toast.LENGTH_LONG).show()
    }

    fun clearButtonClicked()
    {
        TODO("Not implemented yet!...")
    }

    fun closeButtonClicked()
    {
        TODO("Not implemented yet!...")
    }

    fun handleChangeEducation(pos : Int)
    {
        val selected = mBinding.educationAdapter!!.getItem(pos)

        Toast.makeText(this@RegisterActivity, selected, Toast.LENGTH_LONG).show()
    }

    fun handleChangeDay(pos : Int)
    {

    }

    fun handleChangeMonth(pos : Int)
    {

    }

    fun handleChangeYear(pos : Int)
    {

    }
}